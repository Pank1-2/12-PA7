import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileSystem {

    BST<String, FileData> nameTree;
    BST<String, ArrayList<FileData>> dateTree;
    
    public FileSystem() {
    	nameTree = new BST<>();
        dateTree = new BST<>();
    }


    // TODO
    public FileSystem(String inputFile) {
    	// Add your code here
    	this.nameTree = new BST<>();
    	this.dateTree = new BST<>();
    	
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                // Add your code here
                add(data[0], data[1], data[2]);
            }
            sc.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println(e);

        }
    }


    // TODO
    public void add(String name, String dir, String date) {
    	if (name == null) {
    		return;
    	}
    	
    	else if (dir == null) {
    		return;
    	}
    	
    	else if (date == null) {
    		return;
    	}
    	
    	FileData data = nameTree.get(name);
    	
    	if (data != null) {
    		if (data.lastModifiedDate.compareTo(date) > 0) {
    			return;
    		}
    		
    		else {
    			nameTree.remove(name);
    			dateTree.get(data.lastModifiedDate).remove(data);
    		}
    	}
    	
    	data = new FileData(name, dir, date);
    	
    	ArrayList<FileData> listOfDate = dateTree.get(date);
    	
    	if (listOfDate == null) {
    		listOfDate = new ArrayList<>();
    		dateTree.put(date, listOfDate);
    	}
    	
    	nameTree.put(name, data);
    	listOfDate.add(data);
    }


    // TODO
    public ArrayList<String> findFileNamesByDate(String date) {
    	if (date == null) {
    		return null;
    	}
    	
    	ArrayList<FileData> lst = dateTree.get(date);
    	ArrayList<String> result = new ArrayList<>();
    	
    	for (FileData data : lst) {
    		result.add(data.name);
    	}
    	
    	return result;

    }


    // TODO
    public FileSystem filter(String startDate, String endDate) {
    	List<String> date = dateTree.keys();
    	
    	FileSystem files = new FileSystem();
    	
    	for (String file : date) {
    		if (file.compareTo(startDate) >= 0 && file.compareTo(endDate) < 0) {
    			ArrayList<FileData> list = dateTree.get(file);
    			for (FileData f : list) {
    				files.add(f.name, f.dir, f.lastModifiedDate);
    			}
    		}
    	}
    	return files;
    }
    
    
    // TODO
    public FileSystem filter(String wildCard) {
    	List<String> name = nameTree.keys();
    	
    	FileSystem files = new FileSystem();
    	
    	for (String file : name) {
    		if (file.contains(wildCard)) {
    			FileData fileD = nameTree.get(file);
    			files.add(fileD.name, fileD.dir, fileD.lastModifiedDate);
    		}
    	}
    	return files;    }
    
    
    // TODO
    public List<String> outputNameTree(){
    	List<String> lst = nameTree.keys();
    	
    	List<String> result = new ArrayList<>();
    	
    	for (String l : lst) {
    		result.add(l + ": " + nameTree.get(l).toString());
    	}
    	
    	return result;
    }
    
    
    // TODO
    public List<String> outputDateTree(){
    	List<String> lst = dateTree.keys();
    	
    	List<String> result = new ArrayList<>();
    	
    	for (int i = lst.size() - 1; i >= 0; i--) {
    		String k = lst.get(i);
    		
    		ArrayList<FileData> date = dateTree.get(k);
    		
    		for (int j = date.size() - 1; j >= 0; j--) {
    			result.add(k + ": " + date.get(j).toString());
    		}
    		
    	}
    	
    	return result;    
    
    }
    

}

