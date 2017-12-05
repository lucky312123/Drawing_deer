
package drawing_deer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelReader {
    
     public static void readOBJ(String path, Model model) {
        try {
            Scanner in = new Scanner(new FileReader(path));
            String line;
            while (in.hasNext()) {
                line = in.nextLine();
                String lineElements[] = line.split(" ");

                // look for vertices "v "
                if (lineElements[0].equals("v")) {
                    Vec3f v = new Vec3f();
                    v.x = Float.parseFloat(lineElements[1]);
                    v.y = Float.parseFloat(lineElements[2]);
                    v.z = Float.parseFloat(lineElements[3]);
                    model.vertices.add(v);
                    System.out.println(v);
                }
                // look for faces "f "
                if (lineElements[0].equals("f")) {
                    Vec3i f = new Vec3i();
                    int a[] = new int[3];
                    for (int i = 1; i < 4; i++) {
                        String indices[] = lineElements[i].split("/");
                        a[i - 1] = Integer.parseInt(indices[0]);   
                    }
                    f.a = a[0] - 1; // w javie indeksowanie od 0! a w obj od 1.
                    f.b = a[1] - 1;
                    f.c = a[2] - 1;
                    model.faces.add(f);
                    System.out.println(f);
                }
            }
            in.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModelReader.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }
    
}
