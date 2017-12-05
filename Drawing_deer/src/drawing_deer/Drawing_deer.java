
package drawing_deer;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Drawing_deer {
    
    public static void flip(BufferedImage img){
        
        
        
        for (int i = 0; i < img.getHeight(); i++) {
               for (int j = 0; j < img.getWidth() /2; j++) {
                            int tmp = img.getRGB(i, j);
                            img.setRGB(i, j, img.getRGB(i, img.getHeight() - j - 1));
                            img.setRGB(i, img.getHeight() - j - 1, tmp);
               }
        }
    }

    public static void main(String[] args) {

        Model model = new Model();
        ModelReader.readOBJ("C:\\Users\\student\\Desktop\\deer.obj", model);

        try {
            BufferedImage img = new BufferedImage(
                    300, 300, BufferedImage.TYPE_INT_RGB);

            File f = new File("C:\\Users\\student\\Desktop\\Jelen.png");
            int r = 0;
            int g = 0;
            int b = 0;
            int col = (r << 16) | (g << 8) | b;
            for (int x = 0; x < 100; x++) {
                for (int y = 0; y < 100; y++) {
                    img.setRGB(x, y, col);
                }
            }

            System.out.println("Rysownie trójkątów");
            for (int i = 0; i < model.faces.size(); i++) {
                int xa, xb, xc, ya, yb, yc;
                xa = (int) Math.round(((model.vertices.get(model.faces.get(i).a).x) * img.getWidth() / 2) + (img.getWidth() / 2));
                ya = (int) Math.round(model.vertices.get(model.faces.get(i).a).y * img.getHeight() / 2);

                xb = (int) Math.round(((model.vertices.get(model.faces.get(i).b).x) * img.getWidth() / 2) + (img.getWidth() / 2));
                yb = (int) Math.round(model.vertices.get(model.faces.get(i).b).y * img.getHeight() / 2);

                xc = (int) Math.round(((model.vertices.get(model.faces.get(i).c).x) * img.getWidth() / 2) + (img.getWidth() / 2));
                yc = (int) Math.round(model.vertices.get(model.faces.get(i).c).y * img.getHeight() / 2);

                Triangle w = new Triangle();

                img = w.tricnycle(xa, ya, xb, yb, xc, yc, img);

            }
            
            flip(img);
            ImageIO.write(img, "PNG", f);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // funkcja do wczytywania pliku obj, studenci powinni napisac ja sami
        // wczytujemy linie zaczynajace sie od "v" i "f", odpowiednio definicje 
        // wierzchlkow i scian
        // wierzcholek zawiera trzy floaty
        // sciana zawiera 3 wierzcholki w postaci indeksow:
        // indeks_wierzcholka/indeks_normalnej/indeks_tekstury
        // przykĹadowo:
        // f 710/1095/2073 454/702/2074 714/1100/2075
        // interesuja nas indeksy wierzcholkow czyli pierwsza liczba z kazdej trojki
        // trzeba je wydzielic (split("/")) i zapisac do modelu
        // TUTAJ W PETLI WYRYSOWAC TROJKATY 
        // Z KTORYCH SKLADA SIE MODEL
        // zad 1: model siatkowy
        // zad 2: model z zamalowanymi trojkatami
        // klasa model zawiera liste wierzcholkow i liste scian
        // kazda sciana ma 3 wierzcholki (trojkat)
        // element wektora faces, np faces[0] to sciana, a jego elementy (x,y,z)
        // to indeksy wierzcholkow z listy wierzcholow z ktorych sie sklada
        // wiec indeksujemy w stylu  vertices[faces[i].a].x
        // np gdy chcemy odczytac wspolrzedna x wierzcholka A pierwszego trojkata (sciany)
        // modelu to robimy to tak:
        float x = model.vertices.get(model.faces.get(0).a).x;
        float y = model.vertices.get(model.faces.get(0).a).y;
        float z = model.vertices.get(model.faces.get(0).a).z;
        float b = model.vertices.get(model.faces.get(0).b).x;
        float c = model.vertices.get(model.faces.get(1).a).x;

        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        System.out.println(b);
        System.out.println(c);

        // rysowanie modelu siatkowego: rysujemy kazdy trojkat tylko z linii (boki)
        // wspolrzedne ekranu (mnoĹźymy wspolrzedne modelu 3d przez rozmiar zdjecia):
        // x_ekranu = x_modelu * szerokosc / 2 + szerokosc / 2;
        // y_ekranu = y_modelu * wysokosc / 2;
        // tak dla kazdego wierzcholka i rysujemy trojkaty
    }

}
