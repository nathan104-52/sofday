import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends Canvas implements KeyListener {

  private static final long serialVersionUID = 1L;
  private Player player;
  private Background background;

  public Game() {
    this.setPreferredSize(new Dimension(800, 600));
    this.addKeyListener(this);
    this.player = new Player(this);
    this.background = new Background(this);
  }

  @Override
  public void paint(Graphics g) {
    this.background.draw(g);
    this.player.draw(g);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        this.player.moveUp();
        break;
      case KeyEvent.VK_DOWN:
        this.player.moveDown();
        break;
      case KeyEvent.VK_LEFT:
        this.player.moveLeft();
        break;
      case KeyEvent.VK_RIGHT:
        this.player.moveRight();
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  @Override
  public void keyTyped(KeyEvent e) {}

  public static void main(String[] args) {
    Game game = new Game();
    game.setFocusable(true);
    game.requestFocusInWindow();
    new Thread(null, game::run, "Game", 1 << 26).start();
  }

  private void run() {
    while (true) {
      this.repaint();
      try {
        Thread.sleep(1000 / 60);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class Player {

  private Game game;
  private int x;
  private int y;
  private int speed;

  public Player(Game game) {
    this.game = game;
    this.x = 100;
    this.y = 100;
    this.speed = 5;
  }

  public void moveUp() {
    this.y -= this.speed;
  }

  public void moveDown() {
    this.y += this.speed;
  }

  public void moveLeft() {
    this.x -= this.speed;
  }

  public void moveRight() {
    this.x += this.speed;
  }

  public void draw(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(this.x, this.y, 50, 50);
  }
}

class Background {

  private Game game;
  private Image image;

  public Background(Game game) {
    this.game = game;
    this.image = game.getImage("background.png");
  }

  public void draw(Graphics g) {
    g.drawImage(this.image, 0, 0, this.game.getWidth(), this.game.getHeight(), null);
  }
}
