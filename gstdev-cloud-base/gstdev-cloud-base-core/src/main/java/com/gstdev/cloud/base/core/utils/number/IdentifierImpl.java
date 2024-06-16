package com.gstdev.cloud.base.core.utils.number;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.concurrent.ThreadLocalRandom;

public class IdentifierImpl implements Identifier {
    private static Logger logger = LoggerFactory.getLogger(IdentifierImpl.class);
    private static Identifier identifier = new IdentifierImpl();
    private volatile int machine = 0;
    private volatile int serial = 0;

    public IdentifierImpl() {

        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] ip = address.getAddress();
            byte[] mac = ni.getHardwareAddress();
            for (int i = 0, n = ip.length; i < n; i++) {
                this.machine = 31 * this.machine + (ip[i] & 0xFF);
            }
            for (int i = 0, n = mac.length; i < n; i++) {
                this.machine = 31 * this.machine + (mac[i] & 0xFF);
            }
            this.machine = this.machine & 0x7F;
        } catch (Exception e) {
            this.machine = ThreadLocalRandom.current().nextInt() & 0x7F;
            logger.error(String.format("auto get id.machine= %s", this.machine));
        }
    }

    public static synchronized Identifier getIdentifierImpl() {
        return identifier;
    }

    public static void main(String[] args) {
        IdentifierImpl identifier = new IdentifierImpl();
        for (int i = 0; i < 10; i++) {
        }
    }

    @Override
    public synchronized int getMachine() {
        return this.machine;
    }

    @Override
    public synchronized void setMachine(int machine) {
        this.machine = machine & 0x7F;
    }

    @Override
    public synchronized long get() {
        long id = (System.currentTimeMillis() << 20) & (-1L >>> 1);
        id = id | ((this.machine & 0x7F) << 13) | (this.serial & 0x1FFF);
        this.serial = (this.serial + 1) & 0x1FFF;
        return id;
    }
}
