
package drawing_deer;

import java.util.ArrayList;

public class Model {
    
    public Model()
    {
        vertices = new ArrayList<Vec3f>();
        faces = new ArrayList<Vec3i>();
    }

    ArrayList<Vec3f> vertices;
    ArrayList<Vec3i> faces;
    
}
