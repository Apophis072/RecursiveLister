import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveLister extends JFrame implements ActionListener {
    private JButton startBtn, quitBtn;
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private JScrollPane scrollPane;

    public RecursiveLister() {
        setTitle("Recursive File Lister");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.75);
        int height = (int) (screenSize.height * 0.75);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startBtn = new JButton("Start");
        quitBtn = new JButton("Quit");
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        fileChooser = new JFileChooser();

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.add(startBtn);
        panel.add(quitBtn);
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        startBtn.addActionListener(this);
        quitBtn.addActionListener(this);

        textArea.setEditable(false);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                listFiles(selectedFile);
            }
        } else if (e.getSource() == quitBtn) {
            System.exit(0);
        }
    }

    private void listFiles(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File e : files) {
                if (e.isDirectory()) {
                    listFiles(e);
                } else {
                    textArea.append(e.getAbsolutePath() + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RecursiveLister().setVisible(true));
    }
}
