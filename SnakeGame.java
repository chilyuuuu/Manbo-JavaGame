import javax.swing.*; // 导入Swing库，用于GUI编程
import java.awt.*; // 导入AWT库，用于图形和布局
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random; // 导入随机数生成器

public class SnakeGame extends JFrame { // 定义SnakeGame类，继承自JFrame
    private final int WIDTH = 800; // 游戏窗口的宽度
    private final int HEIGHT = 800; // 游戏窗口的高度
    private final int DOT_SIZE = 50; // 蛇头的尺寸
    private int score = 0; // 得分

    private int x; // 蛇头的x坐标
    private int y; // 蛇头的y坐标

    private int apple_x; // 食物的x坐标
    private int apple_y; // 食物的y坐标

    private boolean leftDirection = false; // 蛇向左移动的标志
    private boolean rightDirection = true; // 蛇向右移动的标志
    private boolean upDirection = false; // 蛇向上移动的标志
    private boolean downDirection = false; // 蛇向下移动的标志

    private Image head; // 蛇头的图片
    private Image apple; // 食物的图片
    private Image background; // 背景图片

    private GamePanel gamePanel; // 创建GamePanel的实例
    
    private JButton endGameButton; // 结束游戏按钮

    class GamePanel extends JPanel {
        public GamePanel() {
            setPreferredSize(new Dimension(WIDTH, HEIGHT)); // 设置面板的首选大小为游戏窗口的大小
            setBackground(Color.black); // 设置面板的背景颜色为黑色
            setFocusable(true); // 设置面板可以获取焦点
            addKeyListener(new TAdapter()); // 为面板添加键盘监听器
        }

        @Override
        protected void paintComponent(Graphics g) { // 重写paintComponent方法进行绘制
            super.paintComponent(g); // 调用父类的paintComponent方法，确保正确处理背景
            
            // 绘制背景图片
            if (background != null) {
                g.drawImage(background, 0, 0, this); // 绘制背景
            } else {
                g.setColor(Color.black);
                g.fillRect(0, 0, WIDTH, HEIGHT); // 清屏，用黑色填充整个游戏窗口
            }

            // 绘制得分
            g.setColor(Color.white);
            g.setFont(new Font("宋体", Font.BOLD, 30)); // 设置字体为宋体，支持中文
            g.drawString("抓到曼波数: " + score, 10, 40);

            // 绘制蛇头
            if (head != null) {
                g.drawImage(head, x, y, this); // 绘制蛇头
            }
            
            // 绘制食物
            if (apple != null) {
                g.drawImage(apple, apple_x, apple_y, this); // 绘制食物
            }
        }
    }

    private JPanel bottomPanel; // 创建底部面板用于放置结束按钮

    public SnakeGame() { // 构造函数
        System.out.println("游戏初始化"); // 打印游戏初始化信息
        gamePanel = new GamePanel(); // 创建GamePanel实例
        setGame(); // 初始化游戏
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认关闭操作
        this.setTitle("一起抓曼波"); // 设置窗口标题
        this.setResizable(false); // 设置窗口大小不可调整
        this.setLayout(new BorderLayout()); // 设置窗口布局为BorderLayout
        this.add(gamePanel); // 将GamePanel添加到JFrame中
        this.add(gamePanel, BorderLayout.CENTER); // 将GamePanel添加到JFrame中
        
        
        this.pack(); // 根据组件的首选大小调整窗口大小
        this.setLocationRelativeTo(null); // 将窗口居中
        // 修改窗口位置
        int xPosition = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - (WIDTH + 50)) / 2;
        int yPosition = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (HEIGHT + 50)) / 2;
        this.setLocation(xPosition, yPosition);
        this.setSize(new Dimension(WIDTH+20, HEIGHT+80));
        this.setVisible(true); // 设置窗口可见
        gamePanel.requestFocusInWindow(); // 请求焦点到游戏面板

         // 创建底部面板并设置布局
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.BLACK);

        // 创建结束按钮
        endGameButton = new JButton("结束游戏");
        endGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGameEndPanel();
                // System.exit(0);
            }
        });
        // 将结束按钮添加到底部面板
        bottomPanel.add(endGameButton);

        // 将底部面板添加到JFrame的SOUTH区域
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void setGame() { // 初始化游戏的方法
        System.out.println("设置游戏"); // 打印设置游戏信息
        x = WIDTH / 2; // 设置蛇头的初始x坐标
        y = HEIGHT / 2; // 设置蛇头的初始y坐标
        
        locateApple(); // 随机放置食物
    
        try { // 尝试加载图片资源
            // 蛇头的图片
            head = new ImageIcon("img/head.jpg").getImage().getScaledInstance(DOT_SIZE, DOT_SIZE, Image.SCALE_DEFAULT);
            // 食物的图片
            apple = new ImageIcon("img/apple.jpg").getImage().getScaledInstance(DOT_SIZE, DOT_SIZE, Image.SCALE_DEFAULT);
            // 背景图片
            background = new ImageIcon("img/background.jpg").getImage().getScaledInstance(WIDTH+40, HEIGHT+40, Image.SCALE_DEFAULT);
        } catch (Exception e) { // 捕获异常
            e.printStackTrace(); // 打印异常堆栈
        }
    }

    private void move() { // 移动蛇的方法
        // 检查是否会超出边界，并阻止蛇头移动
        if (leftDirection && x > 0) {
            x -= DOT_SIZE;
        }

        if (rightDirection && x < WIDTH - DOT_SIZE) {
            x += DOT_SIZE;
        }

        if (upDirection && y > 0) {
            y -= DOT_SIZE;
        }

        if (downDirection && y < HEIGHT - DOT_SIZE) {
            y += DOT_SIZE;
        }
    }

    private void checkApple() { // 检查蛇是否吃到食物的方法
        if (x == apple_x && y == apple_y) { // 如果蛇头碰到食物
            score++; // 增加得分
            locateApple(); // 重新放置食物
            gamePanel.repaint(); // 立即重绘游戏面板
        }
    }

    private void locateApple() { // 随机放置食物的方法
        Random random = new Random();
        // 确保食物的坐标在窗口的边界内
        apple_x = random.nextInt(WIDTH / DOT_SIZE) * DOT_SIZE;
        apple_y = random.nextInt(HEIGHT / DOT_SIZE) * DOT_SIZE;
    }

    private class TAdapter extends KeyAdapter { // 键盘监听器的适配器
        @Override
        public void keyPressed(KeyEvent e) { // 处理键盘事件的方法
            System.out.println("按键按下: " + KeyEvent.getKeyText(e.getKeyCode())); // 打印按键信息
            int key = e.getKeyCode(); // 获取按键代码

            // 重置方向
            DirectionFalse();

            // 根据按键更新方向
            switch (key) {
                case KeyEvent.VK_LEFT:
                    leftDirection = true;
                    System.out.println("蛇向左移动");
                    break;
                case KeyEvent.VK_RIGHT:
                    rightDirection = true;
                    System.out.println("蛇向右移动");
                    break;
                case KeyEvent.VK_UP:
                    upDirection = true;
                    System.out.println("蛇向上移动");
                    break;
                case KeyEvent.VK_DOWN:
                    downDirection = true;
                    System.out.println("蛇向下移动");
                    break;
                default:
                    System.out.println("无效的按键");
                    break;
            }
            move(); // 移动蛇
            checkApple(); // 检查蛇是否吃到食物
            gamePanel.repaint(); // 重绘游戏面板
        }
    }

    // 重置方向
    private void DirectionFalse() {
        leftDirection = false;
        rightDirection = false;
        upDirection = false;
        downDirection = false;
    }

    // 显示游戏结束面板
private void showGameEndPanel() {
    // 创建一个新的JFrame来显示游戏结束的信息
    JFrame endFrame = new JFrame("游戏结束");
    endFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    endFrame.setSize(300, 200);
    endFrame.setLayout(new BoxLayout(endFrame.getContentPane(), BoxLayout.Y_AXIS));
    endFrame.setUndecorated(true); // 移除窗口装饰，如边框
    endFrame.setBackground(Color.WHITE); // 设置窗口背景为纯白色
    endFrame.setAlwaysOnTop(true); // 设置窗口始终置顶

    // 创建一个面板来放置标签，以便使用FlowLayout居中
    JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    labelPanel.setBackground(Color.WHITE); // 设置面板背景为纯白色

    // 添加游戏结束的标签，设置字体大小和居中
    JLabel endLabel = new JLabel("游戏结束");
    endLabel.setFont(new Font("Dialog", Font.BOLD, 24)); // 设置字体大小为24
    endLabel.setForeground(Color.BLACK); // 设置字体颜色为纯黑色
    labelPanel.add(endLabel);

    // 添加得分的标签，设置字体大小和居中
    JLabel scoreLabel = new JLabel("你共抓到了 " + score + " 只曼波");
    scoreLabel.setFont(new Font("Dialog", Font.BOLD, 24)); // 设置字体大小为24
    scoreLabel.setForeground(Color.BLACK); // 设置字体颜色为纯黑色
    labelPanel.add(scoreLabel);

    // 添加“再玩一次”按钮，设置字体大小
    JButton playAgainButton = new JButton("再玩一次");
    playAgainButton.setFont(new Font("Dialog", Font.BOLD, 18)); // 设置字体大小为18
    playAgainButton.setForeground(Color.BLACK); // 设置按钮字体颜色为纯黑色
    playAgainButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 关闭当前游戏窗口
            dispose();
            // 创建新的SnakeGame实例重新开始游戏
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new SnakeGame(); // 创建SnakeGame实例
                }
            });
            // 关闭结束窗口
            endFrame.dispose();
        }
    });
    labelPanel.add(playAgainButton);

    endFrame.add(labelPanel);
    endFrame.setLocationRelativeTo(null); // 居中显示
    endFrame.setVisible(true); // 显示窗口
}

    public static void main(String[] args) { // 主方法
        System.out.println("开始游戏"); // 打印开始游戏信息
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SnakeGame(); // 创建SnakeGame实例
            }
        });
    }
}