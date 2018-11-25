package com.algorithm.dp;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划问题
 */
@Slf4j
public class CoinChange {  
	/**  
	 * @param coins  保存每一种硬币的币值的数组  
	 * @param money  需要找零的面值  
	 */ 
	public static void changeCoins(int[] coins,int money) {  
		int[] coinsUsed = new int[money + 1];  	 // 保存面值为i的纸币找零所需的最小硬币数

		int valueKinds = coins.length;		//硬币种类数量

		coinsUsed[0] = 0;  //0元的最优解

		Map<Integer,HashMap<Integer,Integer>> coinChangeMap = new HashMap<>();
 
		for (int cents = 1; cents <= money; cents++) {  
			// 当用最小币值的硬币找零时，所需硬币数量最多  
			int minCoins = cents;  
			HashMap<Integer,Integer> minCoinMap = new HashMap<>();//保存各个面值的具体找零方案
			minCoinMap.put(1, cents);
			coinChangeMap.put(cents, minCoinMap);
			// 遍历每一种面值的硬币，看是否可作为找零的其中之一  
			for (int kind = 0; kind < valueKinds; kind++) {               
				int coinVal = coins[kind];
				int oppCoinVal = cents - coinVal;
				if (coins[kind] <= cents) {  // 若当前面值的硬币小于当前的cents则分解问题并查表  
					int tmpCount = coinsUsed[oppCoinVal] + 1;  
					if (tmpCount <= minCoins) {  
						HashMap<Integer,Integer> subMap = coinChangeMap.get(oppCoinVal);//子问题的最优解
						HashMap<Integer,Integer> tmpMap = new HashMap<>();
						if(subMap != null){//要copy一份数据
							tmpMap.putAll(subMap);
						}
						if(tmpMap.containsKey(coins[kind])){//如果已经包含当前面值，则加一
							tmpMap.put(coins[kind], subMap.get(coins[kind])+1);
						}else{
							tmpMap.put(coins[kind], 1);
						}
						minCoins = tmpCount;  
						minCoinMap = tmpMap;
					}
				}  
			}  
			// 保存最小硬币数  
			coinsUsed[cents] = minCoins; 
			coinChangeMap.put(cents, minCoinMap);
 
			log.info ("总金额 " + (cents) + " 的最小硬币数 : "
					+ coinsUsed[cents]+",货币为"+ coinChangeMap.get(cents));  
		}  
	}  
 
	public static void main(String[] args) {  
		// 硬币面值预先已经按降序排列  
		int[] coinValue = new int[] { 50, 20, 11, 5, 2,1 };  
		// 需要找零的面值  
		int money = 10;
		// 保存每一个面值找零所需的最小硬币数，0号单元舍弃不用，所以要多加1  
		changeCoins(coinValue,  money);  
	}  
 
}
