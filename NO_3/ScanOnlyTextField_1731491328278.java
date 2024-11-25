import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ScanOnlyTextField extends JFrame {
    private boolean isScanning = false;

    public ScanOnlyTextField() {
        // 创建一个 JTextField
        final JTextField textField = new JTextField(20);

        // 添加键盘事件监听器
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // 如果正在扫描，允许输入
                if (isScanning) {
                    textField.append(e.getKeyChar());
                } else {
                    // 阻止所有键盘输入
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // 检测回车键，表示扫描完成
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    isScanning = false;
                } else {
                    // 开始扫描
                    isScanning = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // 阻止所有键盘输入
                e.consume();
            }
        });

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
