package com.gstdev.cloud.service.system.service;

import com.gstdev.cloud.base.core.utils.SecureUtil;
import com.gstdev.cloud.data.core.enums.DataItemStatus;
import com.gstdev.cloud.data.core.service.BaseServiceImpl;
import com.gstdev.cloud.service.system.domain.entity.SysAttribute;
import com.gstdev.cloud.service.system.domain.entity.SysPermission;
import com.gstdev.cloud.service.system.mapper.SysPermissionMapper;
import com.gstdev.cloud.service.system.repository.SysPermissionRepository;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission, String, SysPermissionRepository> implements SysPermissionService {

    @Resource
    private SysAttributeService sysAttributeService;
    @Resource
    private SysPermissionRepository sysPermissionRepository;
    @Resource
    private  SysPermissionMapper sysPermissionMapper;

    public SysPermissionServiceImpl(SysPermissionRepository sysPermissionRepository, SysPermissionMapper sysPermissionMapper) {
        super(sysPermissionRepository);
        this.sysPermissionMapper=sysPermissionMapper;
    }

    public static String generateKey(List<String> input) {
        // 对字符串列表进行排序
        Collections.sort(input);
        // 连接排序后的字符串
        String combinedInput = String.join("", input);
        return SecureUtil.md5(combinedInput);
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(combinedInput.getBytes());
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : hash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) {
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }

    }

    @Override
    public SysPermissionRepository getRepository() {
        return sysPermissionRepository;
    }

    @Override
    public void permissionInit() {
        List<SysAttribute> attributeList = sysAttributeService.findAll();
        permissionInit(attributeList);
    }

    @Override
    public List<String> findDistinctPermissionTypes() {
        return sysPermissionRepository.findDistinctPermissionTypes();
    }

    @Transactional
    public void permissionInit(List<SysAttribute> attributeList) {
        SysPermission allPermissionAll = new SysPermission();
        allPermissionAll.setPermissionId(generateKey(Collections.singletonList("all")));
        allPermissionAll.setPermissionCode(generateKey(Collections.singletonList("all")));
        allPermissionAll.setPermissionName("all");
        allPermissionAll.setPermissionType("all");
        getRepository().saveAndFlush(allPermissionAll);
        Map<String, List<SysAttribute>> attributeMap = attributeList.stream()
                .collect(Collectors.groupingBy(SysAttribute::getServiceId));
        Set<SysPermission> permissionList = new HashSet<>();
        for (Map.Entry<String, List<SysAttribute>> stringListEntry : attributeMap.entrySet()) {
            List<SysAttribute> value = stringListEntry.getValue();
            String key = stringListEntry.getKey();
            this.updateStatusByPermissionType(DataItemStatus.EXPIRED, "service");
            this.updateStatusByPermissionType(DataItemStatus.EXPIRED, key + ":generateCorrelatedKeysService");
            Map<String, Set<SysPermission>> stringListMap = generateCorrelatedKeys(value);
            value.forEach(attribute -> attribute.addPermissions(stringListMap.get(attribute.getAttributeId())));
            for (Set<SysPermission> sysPermissions : stringListMap.values()) {
                permissionList.addAll(sysPermissions);
            }
            String key1 = generateKey(Collections.singletonList(key));
            SysPermission sysPermission = new SysPermission();
            sysPermission.setPermissionId(key1);
            sysPermission.setPermissionCode(key1);
            System.out.println("aaaaaaaaaaaaaaa" + key);
            List<String> strings = Collections.singletonList(key);
            // 对字符串列表进行排序
            Collections.sort(strings);
            // 连接排序后的字符串
            String combinedInput = String.join("", strings);
            sysPermission.setPermissionCodeText(combinedInput);
            sysPermission.setPermissionType("service");
            sysPermission.setPermissionName(key);
            value.forEach(attribute -> attribute.addPermissions(sysPermission));
            value.forEach(attribute -> attribute.addPermissions(allPermissionAll));
            permissionList.add(sysPermission);
        }

        if (!getRepository().existsById(allPermissionAll.getPermissionId())) {
            permissionList.add(allPermissionAll);
        }

        getRepository().saveAllAndFlush(permissionList);
        sysAttributeService.saveAllAndFlush(attributeList);

    }

    public static Map<String, Set<SysPermission>> generateCorrelatedKeys(List<SysAttribute> sysAttributes) {
        List<SysPermission> strings = printCombinations("a", sysAttributes, 0, new ArrayList<>());
        Map<String, Set<SysPermission>> permissionMap = new HashMap<>();
        for (SysPermission string : strings) {
            for (SysAttribute sysAttribute : string.getSysAttributes()) {
                Set<SysPermission> sysPermissions = permissionMap.get(sysAttribute.getAttributeId());
                sysPermissions.addAll(sysPermissions);
                permissionMap.put(sysAttribute.getAttributeId(),sysPermissions);
            }
        }
        return permissionMap;
//        Map<String, Set<SysPermission>> permissionMap = new HashMap<>();
//        for (int i = 0; i < sysAttributes.size(); i++) {
//            SysAttribute attribute1 = sysAttributes.get(i);
//            String attributeId = attribute1.getAttributeId();
//
//            Set<SysPermission> sysPermissions = new HashSet<>();
//            List<String> combinedCodes = new ArrayList<>();
//
//            for (int j = i; j < sysAttributes.size(); j++) {
//
//                SysAttribute attribute2 = sysAttributes.get(j);
//                combinedCodes.add(attribute2.getServiceId() + attribute2.getAttributeCode());
//                String key2 = generateKey(combinedCodes);
//                SysPermission sysPermission1 = new SysPermission();
//                // 对字符串列表进行排序
//                Collections.sort(combinedCodes);
//                // 连接排序后的字符串
//                String combinedInput = String.join("", combinedCodes);
//                sysPermission1.setPermissionCodeText(combinedInput);
//                sysPermission1.setPermissionId(key2);
//                sysPermission1.setPermissionCode(key2);
//                sysPermission1.setPermissionType(attribute2.getServiceId() + ":generateCorrelatedKeysService");
//                sysPermission1.setPermissionName(attribute2.getServiceId() + ":" + combinedCodes.size() + ":" + attribute1.getClassName() + j);
//                sysPermissions.add(sysPermission1);
//                if(sysAttributes.get(i).getAttributeCode().equals("gstdev-systemget:v1:account:get-account-settings-detailgstdev-systemget:v1:system:constant:enumsgstdev-systemget:v1:user:get-user-settings-detailgstdev-systempost:v1:account:update-account-settings-detailgstdev-systemput:v1:security:reset-password:originalPassword:newPasswordgstdev-systemput:v1:security:update-account-current-login-information")){
//
//                    System.out.println("aaaaaaaaaaaaaa"+combinedInput+combinedCodes.size());
//                    if(combinedCodes.size()==6){
//                        System.out.println("ccccccc"+combinedCodes);
//                    }
//                }
//            }
//
//            permissionMap.put(attributeId, sysPermissions);
//        }
//        return permissionMap;
    }

    public void updateStatusByPermissionType(DataItemStatus status, String permissionType) {
        getRepository().updateStatusByPermissionType(status, permissionType);
    }

    /**
     *     public static List<String> printCombinations(List<String> array, int start, ArrayList<String> prefix) {
     *         ArrayList<String> objects = new ArrayList<>();
     *         objects.add(generateKey(prefix));
     *         String s = generateKey(prefix);
     *         System.out.println("aaaaaaaaaaaa"+prefix);
     *         for (int i = start; i < array.size(); i++) {
     *             ArrayList<String> newPrefix = new ArrayList<>(prefix);
     *             newPrefix.add(array.get(i));
     *             objects.addAll(printCombinations(array, i + 1, newPrefix));
     *         }
     *         return objects;
     *     }
     *
     *
     *     public static void main(String[] args) throws StripeException {
     * //            int[] array = {0, 1, 2, 3};
     *         List<String> combinedCodes = new ArrayList<>();
     *         combinedCodes.add("0");
     *         combinedCodes.add("1");
     *         combinedCodes.add("2");
     *         combinedCodes.add("3");
     *         List<String> strings = printCombinations(combinedCodes, 0, new ArrayList<>());
     *         System.out.println(strings);
     *
     * @param serviceId
     * @param array
     * @param start
     * @param prefix
     * @return
     */
    public static List<SysPermission> printCombinations(String serviceId, List<SysAttribute> array, int start, ArrayList<SysAttribute> prefix) {
        List<SysPermission> sysPermissions = new ArrayList<>();
        List<String> collect = prefix.stream().map(SysAttribute::getAttributeCode).collect(Collectors.toList());
        String key = generateKey(collect);
        Collections.sort(collect);
        // 连接排序后的字符串
        String combinedInput = String.join("", collect);
        SysPermission sysPermission = new SysPermission();
        sysPermission.setPermissionCodeText(combinedInput);
        sysPermission.setPermissionId(key);
        sysPermission.setPermissionCode(key);
        sysPermission.setPermissionType(serviceId + ":generateCorrelatedKeysService");
        sysPermission.setPermissionName(serviceId + ":" + collect.size() + ":");
        sysPermission.setSysAttributes(array);
        sysPermissions.add(sysPermission);
        for (int i = start; i < array.size(); i++) {
            ArrayList<SysAttribute> newPrefix = new ArrayList<>(prefix);
            newPrefix.add(array.get(i));
            sysPermissions.addAll(printCombinations(serviceId, array, i + 1, newPrefix));
        }
        return sysPermissions;
    }
}

