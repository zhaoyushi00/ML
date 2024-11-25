import javax.swing.*;
import java.awt.event.KeyEvent;

public class ScanOnlyTextField extends JFrame {
    public ScanOnlyTextField() {
        // 创建一个 JTextField
        JTextField textField = new JTextField(20) {
            @Override
            protected void processKeyEvent(KeyEvent e) {
                // 阻止所有键盘输入
                if (e.getID() == KeyEvent.KEY_TYPED) {
                    return;
                }
                super.processKeyEvent(e);
            }
        };

        // 添加 JTextField 到 JFrame
        add(textField);

        // 设置 JFrame 属性
        setTitle("Scan Only TextField");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中显示
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ScanOnlyTextField().setVisible(true);
        });
    }
}
