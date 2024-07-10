package cn.ztion.sakura.wapper;

import cn.ztion.sakura.util.ImageTransferable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class ImageDialog extends DialogWrapper {

    private final BufferedImage image;
    private final int width;
    private final int height;

    public ImageDialog(Project project, BufferedImage image, int width, int height) {
        super(project);
        this.width = width;
        this.height = height;
        this.image = image;
        setTitle("预览");
        init();
    }

    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());


        double aspectRatio = (double) image.getWidth() / image.getHeight();
        int scaledWidth = width;
        int scaledHeight = (int) (width / aspectRatio);

        if (scaledHeight > height) {
            scaledHeight = height;
            scaledWidth = (int) (height * aspectRatio);
        }

        ImageIcon imageIcon = new ImageIcon(image);

        Image scaledInstance = imageIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(scaledInstance);

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setPreferredSize(new Dimension(width, height));

        panel.add(imageLabel, BorderLayout.CENTER);

        // Set preferred size for the panel
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }

    @Override
    protected void init() {
        super.init();
        getWindow().setSize(width + 40, height + 40);
        getWindow().setLocationRelativeTo(null);
    }

    @Override
    protected Action[] createActions() {
        Action okAction = new DialogWrapperAction("复制到剪切板") {
            @Override
            protected void doAction(ActionEvent e) {
                ImageTransferable.copyImageToClipboard(image);
                doOKAction();
            }
        };

        Action cancelAction = new DialogWrapperAction("关闭") {
            @Override
            protected void doAction(ActionEvent e) {
                doCancelAction();
            }
        };

        return new Action[]{okAction, cancelAction};
    }

}