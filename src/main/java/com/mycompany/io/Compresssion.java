/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.io;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 *
 * @author nimzo
 */
public class Compresssion {

    private static String jpg = "C:\\Users\\nimzo\\013.jpg";
    private static String jpg_x = "C:\\Users\\nimzo\\013_x.jpg";
    private static String jpg_y = "C:\\Users\\nimzo\\013_y.jpg";

    public static void main(String[] args) {
        new Compresssion().main();
    }

    public void main() {
        try {
            Path input = Paths.get(jpg);
            BufferedImage image = ImageIO.read(input.toFile());
            Path output = Paths.get(jpg_x);
            writeImageWithCompression(output, image, 10);
        } catch (IOException ex) {
            Logger.getLogger(Compresssion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * ファイルの書き込み（圧縮）
     * サポート画像形式：jpg, bmp, gif, png, wbmp, jpeg
     *
     * @param output
     * @param image
     * @param quality
     */
    public static void writeImageWithCompression(Path output, BufferedImage image, int quality) {
        try {
            try (OutputStream os = new FileOutputStream(output.toFile());
                    ImageOutputStream ios = ImageIO.createImageOutputStream(os)) {
                String sufix = getSuffix(output.toString());
                Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(sufix);
                ImageWriter writer = writers.next();
                writer.setOutput(ios);

                ImageWriteParam param = writer.getDefaultWriteParam();
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(quality / 100f);
                writer.write(null, new IIOImage(image, null, null), param);
                writer.dispose();
            }
        } catch (IOException ex) {
            Logger.getLogger(Compresssion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * ファイル名から拡張子を返します。
     *
     * @param fileName ファイル名
     * @return ファイルの拡張子
     */
    public static String getSuffix(String fileName) {
        int point = fileName.lastIndexOf(".");
        return point != -1 ? fileName.substring(point + 1) : fileName;
    }

}
