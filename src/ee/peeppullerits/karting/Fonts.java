package ee.peeppullerits.karting;

public class Fonts {
	TrueTypeFont font;
	02.TrueTypeFont font2;
	03. 
	04.public void init() {
	05.// load a default java font
	06.Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
	07.font = new TrueTypeFont(awtFont, false);
	08. 
	09.// load font from a .ttf file
	10.try {
	11.InputStream inputStream = ResourceLoader.getResourceAsStream("myfont.ttf");
	12. 
	13.Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
	14.awtFont2 = awtFont2.deriveFont(24f); // set font size
	15.font2 = new TrueTypeFont(awtFont2, false);
	16. 
	17.} catch (Exception e) {
	18.e.printStackTrace();
	19.}  
	20.}
}
