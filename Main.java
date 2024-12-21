import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main() {
        // 设置窗口标题
        setTitle("游戏启动器");
        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口布局为FlowLayout，以使每个组件根据内容自动调整大小
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 参数分别为对齐方式、水平间距、垂直间距
        // 设置窗口大小
        setSize(400, 200); // 增加窗口宽度以适应内容
        // 居中显示窗口
        setLocationRelativeTo(null);
        this.setResizable(false);

        // 添加窗口内容
        JLabel promptLabel = new JLabel("启动你想玩的游戏", SwingConstants.CENTER);
        promptLabel.setFont(new Font("Dialog", Font.BOLD, 24)); // 设置字体大小为24
        
        // 添加提示标签
        add(promptLabel);

        // 添加一个透明的面板来实现换行
        add(new JPanel()); // 透明面板，无布局管理器

        JButton chessButton = new JButton("启动五子曼波");
        chessButton.setFont(new Font("Dialog", Font.BOLD, 24)); // 设置字体大小为24

        JButton snakeButton = new JButton("启动一起抓曼波");
        snakeButton.setFont(new Font("Dialog", Font.BOLD, 24)); // 设置字体大小为24

        // 添加按钮的事件监听器
        chessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 运行 StartChessJFrame.java
                try {
                    String className = "StartChessJFrame";
                    Class<?> clazz = Class.forName(className);
                    Object obj = clazz.getDeclaredConstructor().newInstance();
                    if (obj instanceof JFrame) {
                        JFrame frame = (JFrame) obj;
                        frame.setVisible(true);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        snakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 运行 SnakeGame.java
                try {
                    String className = "SnakeGame";
                    Class<?> clazz = Class.forName(className);
                    Object obj = clazz.getDeclaredConstructor().newInstance();
                    if (obj instanceof JFrame) {
                        JFrame frame = (JFrame) obj;
                        frame.setVisible(true);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // 将组件添加到窗口
        add(promptLabel);
        add(chessButton);
        add(snakeButton);

        // 设置窗口可见
        setVisible(true);
    }

    public static void main(String[] args) {
        // 在事件分派线程中创建和显示这个启动器窗口
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}