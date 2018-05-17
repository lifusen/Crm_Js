package cn.itproject.crm.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SignRankingTest {
	public static void main(String[] args) {
		Double[] doubles = {80.0,59.8,35.5,60.9,46.1,48.9,35.5,59.1,30.0,60.9};
		Map<Double, List<Double>> map = new LinkedHashMap<Double, List<Double>>();
		System.out.println(doubles.length);
		Arrays.sort(doubles);
		for (int i = 0; i < doubles.length-1; i++) {
			for (int j = 0; j < doubles.length-1-i; j++) {
				Double temp = doubles[j];
				if (temp<doubles[j+1]) {
					doubles[j] = doubles[j+1];
					doubles[j+1] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(doubles));
		
		for (Double double1 : doubles) {
			System.out.println(double1);
			if (map.containsKey(double1)) {
				map.get(double1).add(double1);
			}else {
				List<Double> list = new ArrayList<Double>();
				list.add(double1);
				
				map.put(double1, list);
			}
		}
		System.out.println(map.size());
		int i = 0;
		for (Entry<Double, List<Double>> kv : map.entrySet()) {
			i++;
			List<Double> list = kv.getValue();
			System.out.println("第"+i+"名:"+list);
		}
		
	}
}
