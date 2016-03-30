package MainTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.itcast.ssi.sysmgmt.vo.UserVO;

import com.google.gson.Gson;

public class MainTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		bb();
//		aa();
//		cc();
//		dd();
//		check();
		
		testJson();
	}
	
	public static void testJson() {
		UserVO uservo = new UserVO();
		Gson gson = new Gson();
		String json = gson.toJson(uservo);
		System.out.println(json);
	}
	
	public static void check() throws Exception{
		BufferedReader br1 = new BufferedReader(new FileReader("doc/aa-json-temp"));
		BufferedReader br2 = new BufferedReader(new FileReader("doc/aa-temp"));
		
		String line = null; 
		List<String> list1 = new ArrayList<String>();
		while((line = br1.readLine()) != null) {
			line = line.substring(line.indexOf("-") + 1);
			list1.add(line);
		}
		
		String line2 = null; 
		List<String> list2 = new ArrayList<String>();
		while((line2 = br2.readLine()) != null) {
			list2.add(line2);
		}
		System.out.println(list1.size());
		System.out.println(list2.size());
		list2.retainAll(list1);
		System.out.println("_______________________");
		System.out.println(list1.size());
		System.out.println(list2.size());
		
		Collections.sort(list2);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(list2);
		System.out.println(jsonResult);
		// ["glass","music","search","envelope","heart","star","user","film","th-large","th","th-list","remove","signal","cog","trash","home","file","road","download","upload","inbox","play-circle","repeat","refresh","list-alt","lock","flag","headphones","volume-off","volume-down","volume-up","qrcode","barcode","tag","tags","book","bookmark","print","camera","font","bold","italic","text-height","text-width","align-left","align-center","align-right","align-justify","list","pencil","map-marker","adjust","tint","edit","share","check","step-backward","fast-backward","backward","play","pause","stop","forward","fast-forward","step-forward","eject","chevron-left","chevron-right","arrow-left","arrow-right","arrow-up","arrow-down","share-alt","plus","minus","asterisk","gift","leaf","fire","plane","calendar","random","comment","magnet","chevron-up","chevron-down","retweet","shopping-cart","folder-open","bullhorn","bell","certificate","thumbs-up","thumbs-down","globe","wrench","tasks","filter","briefcase"]
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("doc/fa-icon-result"));
		for (String s : list2) {
			bw.write(s);
			bw.newLine();
		}
		bw.close();
		br1.close();
		br2.close();
	}

	private static void dd() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("doc/aa-json-last"));
//		BufferedWriter bw = new BufferedWriter(new FileWriter("doc/aa-json"));
		
		List<String> list = new ArrayList<>();
		String line = null;
		while((line = br.readLine()) != null) {
			list.add(line);
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println(list);
		System.out.println(json);
//		String line = null; 
//		List<String> list1 = new ArrayList<String>();
//		while((line = br.readLine()) != null) {
//			line = line.substring(line.indexOf("-") + 1);
//			list1.add(line);
//			bw.write(line);
//			bw.newLine();
//		}
		
//		bw.close();
//		bw.close();
	}
	
	private static void cc() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("doc/fa-json"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("doc/fa-json-temp"));
		
		String line = br.readLine();
		Gson gson = new Gson();
		List<String> list = gson.fromJson(line, List.class);
		System.out.println(list);
		for (String s : list) {
			s = s.substring(s.indexOf("-") + 1);
			bw.write(s);
			bw.newLine();
		}
		bw.close();
		bw.close();
	}
	
	private static void bb() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("doc/Untitled1.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("doc/icons1"));
		
		String line = br.readLine();
		Gson gson = new Gson();
		List list = gson.fromJson(line, List.class);
		System.out.println(list);
		for (Object object : list) {
			bw.write(object + "");
			bw.newLine();
		}
		bw.close();
		bw.close();
	}
	
	public static void aa() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("doc/icons"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("doc/icons2"));
		
		String line = br.readLine();
		Gson gson = new Gson();
		List list = gson.fromJson(line, List.class);
		System.out.println(list);
		for (Object object : list) {
			bw.write(object + "");
			bw.newLine();
		}
		bw.close();
		bw.close();
	}

}
