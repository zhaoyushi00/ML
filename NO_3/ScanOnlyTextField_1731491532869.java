import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class ScanOnlyTextField extends JFrame {
    private boolean isScanning = false;
    private Timer timer = new Timer();

    public ScanOnlyTextField() {
        // 创建一个 JTextField
        final JTextField textField = new JTextField(20);

        // 添加键盘事件监听器
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (isScanning) {
                    // 允许条形码扫描器输入
                    textField.append(e.getKeyChar());
                } else {
                    // 阻止所有键盘输入
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // 检测回车键，表示扫描完成
                    isScanning = false;
                    timer.cancel();
                } else {
                    // 开始扫描
                    isScanning = true;
                    // 启动计时器，如果在短时间内没有新的输入，则认为扫描结束
                    timer.cancel();
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            isScanning = false;
                        }
                    }, 500); // 500毫秒内没有新的输入则认为扫描结束
                }
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
