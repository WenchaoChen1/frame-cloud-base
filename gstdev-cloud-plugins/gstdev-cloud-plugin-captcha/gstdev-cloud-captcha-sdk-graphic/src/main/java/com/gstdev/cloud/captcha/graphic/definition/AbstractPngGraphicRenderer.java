package com.gstdev.cloud.captcha.graphic.definition;

import com.gstdev.cloud.captcha.core.definition.domain.Metadata;
import com.gstdev.cloud.commons.ass.definition.constants.SymbolConstants;
import org.apache.commons.lang3.StringUtils;

import java.awt.image.BufferedImage;

/**
 * <p>Description: Png 类型图形验证码绘制器 </p>
 *
 * @author : cc
 * @date : 2024/12/21 23:17
 */
public abstract class AbstractPngGraphicRenderer extends AbstractBaseGraphicRenderer {

    @Override
    public Metadata draw() {
        String[] drawCharacters = this.getDrawCharacters();

        BufferedImage bufferedImage = createPngBufferedImage(drawCharacters);

        String characters = StringUtils.join(drawCharacters, SymbolConstants.BLANK);

        Metadata metadata = new Metadata();
        metadata.setGraphicImageBase64(toBase64(bufferedImage));
        metadata.setCharacters(characters);

        return metadata;
    }
}
