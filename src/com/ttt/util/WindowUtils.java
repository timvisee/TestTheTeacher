package com.ttt.util;

import javax.swing.UIManager;

public class WindowUtils {

    //public static final String APP_ICON_RES = "/icon.png";

    /**
     * Try to use the native look and feel of the system
     */
    public static void useNativeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            System.out.println("Failed to set native GUI look and feel!");
        }
    }

    /*
    /**
     * Set icon of a window to the default icon of the application
     * @param w Window to change the icon of
     * /
    public static void setWindowIcon(Window w) {
        setWindowIcon(w, APP_ICON_RES);
    }

    /**
     * Set the icon if a window
     * @param w Window to change the icon of
     * @param res Icon resource directory
     * /
    public static void setWindowIcon(Window w, String res) {
        try {
            InputStream in = BlackBox.class.getResourceAsStream(res);
            if(in != null)
                w.setIconImage(ImageIO.read(in));
        } catch(IOException e) { }
    }

    public static void setDialogIcon(JDialog d, String res) {
        try {
            InputStream in = BlackBox.class.getResourceAsStream(res);
            if(in != null)
                d.setIconImage(ImageIO.read(in));
        } catch(IOException e) { }
    }*/
}
