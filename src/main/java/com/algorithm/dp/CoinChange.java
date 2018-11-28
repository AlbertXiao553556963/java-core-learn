package com.algorithm.dp;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * 动态规划-硬币找零
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
		for(int i = 1 ; i <= money; i++) {
			for(int j = 0; j < valueKinds; j++) {
				if(i >= coins[j]) {
					int temp =  coinsUsed[i - coins[j]] + 1;
					if(coinsUsed[i] == 0) {
						coinsUsed[i]  =temp;
					}
					coinsUsed[i] = Math.min(coinsUsed[i], temp);
				}
			}
		}
		log.info("找零:{} 需要硬币个数:{}", money, coinsUsed[money]);
	}  
 
	public static void main(String[] args) {  
		// 硬币面值预先已经按降序排列  
		int[] coinValue = new int[] { 50, 20, 11, 5, 2,1 };  
		// 需要找零的面值
		while (true) {
			System.out.println("请输入需要找零的面值:");
			Scanner in = new Scanner(System.in);
			int money = Integer.valueOf(in.nextLine());
			// 保存每一个面值找零所需的最小硬币数，0号单元舍弃不用，所以要多加1
			changeCoins(coinValue,  money);
		}
	}
 
}
