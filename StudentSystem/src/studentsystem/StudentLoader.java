/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsystem;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author cstuser
 */
public class StudentLoader {
    public static void testLoad(String pathStr) throws IOException {
        StudentLoader stloader = new StudentLoader();
        final ArrayList<Student> students = stloader.load(Paths.get(pathStr));
        for(final Student st : students) {
            System.out.println(st.getId() + ", " + st.getName());
        }
    }
    
    public ArrayList<Student> load(Path path) throws IOException {
        final ArrayList<Student> result = new ArrayList<Student>();
//        final Student[] result = new Student[100];
        
        try (final Scanner fileReader = new Scanner(path)) {
//            int k =0;
            while(fileReader.hasNext()) {
                String stId = fileReader.next();
                String stName = fileReader.next();
                result.add(new Student(stId, stName));
  //              ++k;
            }
        }
        
        return result;
    }
}
