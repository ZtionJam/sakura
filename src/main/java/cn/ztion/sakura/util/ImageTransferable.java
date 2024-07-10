package cn.ztion.sakura.util;

import com.intellij.util.ui.ImageUtil;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;

public class ImageTransferable implements Transferable {
    private final BufferedImage image;

    public ImageTransferable(BufferedImage image) {
        this.image = ImageUtil.createImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = this.image.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.imageFlavor};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return DataFlavor.imageFlavor.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (!isDataFlavorSupported(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        return image;
    }

    public static void copyImageToClipboard(BufferedImage image) {
        ImageTransferable transferable = new ImageTransferable(image);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(transferable, null);
    }
}