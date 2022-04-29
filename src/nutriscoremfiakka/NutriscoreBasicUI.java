package nutriscoremfiakka;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author arthu
 */
public class NutriscoreBasicUI extends ComponentUI {

    private NutriscoreModel model;

    private Color bgOuterColor = Color.gray;
    private Color bgColor = Color.white;
    private Color textColor = Color.gray.darker();
    private String text = "NUTRI-SCORE";
    private String currentFont = "TimesRoman";
    private int xText = 20;
    private int yText = 25;
    private int fontSize;
    private int minFontSize = 25;
    private int fontSizeIncrement = 5;
    private int arc = 45;
    private int marginV = 40;
    private int marginH = 30;
    private int innerBorder = 10;
    private int outerBorder = 12;
    private ArrayList<Color> colorList;

    public NutriscoreBasicUI() {
        this(null);
    }

    public NutriscoreBasicUI(NutriscoreModel newModel) {
        model = newModel;
        colorList = new ArrayList<>();
        prepareColors();
    }

    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    public void setModel(NutriscoreModel newModel) {
        if (model != newModel) {
            model = newModel;
        }
    }

    @Override
    public void paint(Graphics g, JComponent c) {

        int w = g.getClip().getBounds().width;
        int h = g.getClip().getBounds().height;

        // Draw the background
        g.setColor(bgOuterColor);
        g.fillRect(0, 0, w, h);
        g.setColor(bgColor);
        g.fillRoundRect(0, 0, w, h, arc, arc);

        // Draw the text in the upper part
        g.setColor(textColor);
        fontSize = Math.max(minFontSize, w / 10);
        g.setFont(new Font(currentFont, Font.BOLD, fontSize));
        yText = fontSize;
        g.drawString(text, xText, yText);

        int letterWidth = (w - 2 * marginH) / 5;
        int letterAreaHeight = h - 2 * marginV - yText;

        // Draw the five colored rectangles
        drawFiveColoredRectangles(g, letterWidth, letterAreaHeight);

        // Draw the letter corresponding to the current state of the model
        drawCurrentState(g, letterWidth, letterAreaHeight);
    }

    private void drawCurrentState(Graphics g, int letterWidth, int letterAreaHeight) {

        int currentValue = model.getValue();

        int xRectangle = marginH + currentValue * letterWidth;
        int yRectangle = yText + marginV;

        // Thin outline
        g.setColor(bgColor);

        g.fillRoundRect(xRectangle - outerBorder, yRectangle - outerBorder, letterWidth + 2 * outerBorder, letterAreaHeight + 2 * outerBorder, arc, arc);

        // Colored patch
        Color currentColor = colorList.get(currentValue);

        g.setColor(currentColor.darker());
        g.fillRoundRect(xRectangle - innerBorder, yRectangle - innerBorder, letterWidth + 2 * innerBorder, letterAreaHeight + 2 * innerBorder, arc, arc);

        // Character
        g.setColor(bgColor);
        char letter = (char) ('A' + model.getValue());

        g.setFont(new Font(currentFont, Font.BOLD, fontSize + fontSizeIncrement));
        int fontHeight = g.getFontMetrics().getAscent() + g.getFontMetrics().getDescent();
        int xLetter = marginH + currentValue * letterWidth;
        int yLetter = yText + marginV;

        g.drawString(letter + "", xLetter + letterWidth / 4,
                yLetter + letterAreaHeight / 2 + fontHeight / 2);

    }

    private void drawFiveColoredRectangles(Graphics g, int letterWidth, int letterAreaHeight) {
        for (int i = 0; i < 5; i++) {
            // Draw the background for the i-th letter
            Color currentColor = colorList.get(i);
            g.setColor(currentColor.darker());
            int xLetter = marginH + i * letterWidth;
            int yLetter = yText + marginV;

            switch (i) {
            case 0:
                // Draw a rounded rectangle
                g.fillRoundRect(xLetter, yLetter, letterWidth, letterAreaHeight, arc, arc);
                // Fill in the two corners on the right-hand side
                g.fillRect(xLetter + letterWidth / 2, yLetter, letterWidth, letterAreaHeight);
                break;
            case 4:
                // Draw a rounded rectangle
                g.fillRoundRect(xLetter, yLetter, letterWidth, letterAreaHeight, arc, arc);
                // Fill in the two corners on the left-hand side
                g.fillRect(xLetter, yLetter, letterWidth / 2, letterAreaHeight);
                break;
            default:
                g.fillRect(xLetter, yLetter, letterWidth, letterAreaHeight);
                break;
            }

            // Draw the character in each rectangle
            g.setColor(currentColor.brighter());
            char letter = (char) ('A' + i);
            int fontHeight = g.getFontMetrics().getAscent() + g.getFontMetrics().getDescent();
            g.drawString(letter + "", xLetter + letterWidth / 4, yLetter + letterAreaHeight / 2 + fontHeight / 2);
        }
    }

    private void prepareColors() {
        colorList.clear();
        colorList.add(Color.green.darker());
        colorList.add(Color.green);
        colorList.add(Color.yellow.darker());
        colorList.add(Color.orange);
        colorList.add(Color.red);
    }

}
