package com.jlunchbox.core.graphics.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/* OBJ Layout
 * 
 * v f f f (vector)
 * vn f f f (normal)
 * vt f f (uv)
 * f int int int ... (face)
 *   int/int ... , int/int/int ...
 * g name (submesh name)
 * 
 * mtllib file
 * Ka		- Ambient
 * Kd		- Diffuse
 * Ks		- Specular
 * illum	- Lighting
 * Ns		- Shininess
 * map_kd   - Texture Map
 */
public class ObjLoader {
	
	public ObjLoader () { }

	public void load(String filename) {
		
		String line;
		String[] tokens;
		char first;
		
		FileReader fr = null;
		try { fr = new FileReader(new File(filename)); }
		catch (FileNotFoundException e) {
			System.err.println("Couldn't load file!");
			e.printStackTrace();
		} BufferedReader reader = new BufferedReader(fr);
		
		ArrayList<Float> pos = new ArrayList<Float>();
		ArrayList<Float> uv = new ArrayList<Float>();
		ArrayList<Float> norm = new ArrayList<Float>();
		
	
		try {
			while((line = reader.readLine()) != null) {

				tokens = line.split("\\s+");
				if (tokens.length < 1) break; // EOF
				
				if (tokens[0].length() == 0) { continue; } // blank line
				else if ((first = tokens[0].toLowerCase().charAt(0)) == '#') { continue; } // comment line

				else if(first == 'v') { // vertex data
					
					if (tokens[0].length() == 1) { // position
						pos.add(Float.parseFloat(tokens[1]));
						pos.add(Float.parseFloat(tokens[2]));
						pos.add(Float.parseFloat(tokens[3]));
					} else if (tokens[0].charAt(1) == 'n') { // normal
						norm.add(Float.parseFloat(tokens[1]));
						norm.add(Float.parseFloat(tokens[2]));
						norm.add(Float.parseFloat(tokens[3]));
					} else if (tokens[0].charAt(1) == 't') { // uv
						uv.add(Float.parseFloat(tokens[1]));
						uv.add(Float.parseFloat(tokens[2]));
					}
				} else if (first == 'f') { // face data
					for(String token : tokens)
						System.out.println("[" + token + "]");
					
					if (tokens.length == 5) { // data represents a quad
						
					}
					
					String[] parts = tokens[1].split("/");
					if (parts.length > 2) { // has all parts
						for(String part : parts)
							System.out.println("[" + part + "]");
						
					}
					break;
				} else if (first == 'o' || first == 'g') { // new section
					
				}
					
			}
		} catch (IOException e) {
			System.err.println("Error occured reading OBJ file.");
			e.printStackTrace();
			//return null;
		}

	}
}
