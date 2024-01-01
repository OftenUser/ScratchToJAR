import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.util.zip.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;

public class ToJarFrame extends JFrame {

    /** Creates new form ToJarFrame */
    public ToJarFrame() {
        initComponents();
        outputtarget.setText(System.getProperty("user.home") + System.getProperty("file.separator") + "ScratchBugOnAPlate.jar");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code"> // GEN-BEGIN:initComponents
    private void initComponents() {

        addrLabel = new JLabel();
        titleLabel = new JLabel();
        address = new JTextField();
        title = new JTextField();
        compile = new JButton();
        targetLabel = new JLabel();
        outputtarget = new JTextField();
        select = new JButton();
        progress = new JProgressBar();
        jButton1 = new JButton();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("ScratchToJAR");
        this.setLocationByPlatform(true);
        this.setResizable(false);

        addrLabel.setText("Project:");

        titleLabel.setText("Title:");

        address.setText("C:/scratch-project.sb");

        title.setText("Bug on a plate by bernatp");

        compile.setText("Compile");
        compile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                compileActionPerformed(evt);
            }
        });

        targetLabel.setText("Target:");

        outputtarget.setText("C:/");

        select.setText("Select");
        select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });

        jButton1.setText("Select");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(addrLabel)
                    .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(targetLabel))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(progress, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(compile))
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(outputtarget, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(select))
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                            .addComponent(title, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                            .addComponent(address, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(addrLabel)
                    .addComponent(address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleLabel))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(select)
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(outputtarget, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(targetLabel)))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(compile)
                    .addComponent(progress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    } // </editor-fold>//GEN-END:initComponents
    private JFileChooser jfc = new JFileChooser();

    private void selectActionPerformed(ActionEvent evt) { // GEN-FIRST:event_selectActionPerformed
        jfc.showSaveDialog(this);
        File selected = jfc.getSelectedFile();
        if (selected != null) {
            outputtarget.setText(selected.getAbsolutePath());
        }
    } // GEN-LAST:event_selectActionPerformed

    private void compileActionPerformed(ActionEvent evt) { // GEN-FIRST:event_compileActionPerformed
        progress.setIndeterminate(true);
        outputtarget.setEditable(false);
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputtarget.getText()));
            ZipInputStream in = new ZipInputStream(ToJarFrame.class.getResourceAsStream("ScratchDesktop.dat"));
            ZipEntry entry;
            byte[] buf = new byte[1024];
            int len;
            while ((entry = in.getNextEntry()) != null) {
                out.putNextEntry(new ZipEntry(entry.getName()));
                if (!entry.getName().equals("config.xml") && !entry.getName().equals("project.dat")) {
                    while ((len = in.read(buf)) != -1) {
                        out.write(buf, 0, len);
                    }
                } else {
                    if (entry.getName().equals("project.dat")) {
                        FileInputStream fis = new FileInputStream(address.getText());
                        while ((len = fis.read(buf)) != -1) {
                            out.write(buf, 0, len);
                        }
                        fis.close();
                    } else {
                        Properties p = new Properties();
                        p.setProperty("title", title.getText());
                        // p.setProperty("codebase", "http://scratch.mit.edu/");
                        // p.setProperty("project", "projects/" + address.getText() + "/downloadsb");
                        p.setProperty("project", "project.dat");
                        p.setProperty("autostart", "true");
                        p.setProperty("compiler-version", "1.1");
                        p.setProperty("compiler-time", System.currentTimeMillis() + "");
                        p.storeToXML(out, null);
                    }
                }
                out.closeEntry();
                in.closeEntry();
            }
            in.close();
            out.close();
            JOptionPane.showMessageDialog(this, "Sucessfully Created:\n" + outputtarget.getText());
            Runtime.getRuntime().exec(new String[]{"java", "-jar", outputtarget.getText()});
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        outputtarget.setEditable(true);
        progress.setIndeterminate(false);
    } // GEN-LAST:event_compileActionPerformed

    private void jButton1ActionPerformed(ActionEvent evt) { // GEN-FIRST:event_jButton1ActionPerformed
        jfc.showOpenDialog(this);
        if (jfc.getSelectedFile() != null) {
            address.setText(jfc.getSelectedFile().getAbsolutePath());
        }
    } // GEN-LAST:event_jButton1ActionPerformed

    private boolean isInteger(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ToJarFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ToJarFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ToJarFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ToJarFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ToJarFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - Do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addrLabel;
    private javax.swing.JTextField address;
    private javax.swing.JButton compile;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField outputtarget;
    private javax.swing.JProgressBar progress;
    private javax.swing.JButton select;
    private javax.swing.JLabel targetLabel;
    private javax.swing.JTextField title;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
