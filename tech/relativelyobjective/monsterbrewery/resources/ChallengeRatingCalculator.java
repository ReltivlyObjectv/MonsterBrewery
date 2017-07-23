package tech.relativelyobjective.monsterbrewery.resources;

import java.util.LinkedList;
import java.util.List;
import tech.relativelyobjective.monsterbrewery.attributes.Action;
import tech.relativelyobjective.monsterbrewery.attributes.Spellcaster;

/**
 *
 * @author ReltivlyObjectv
 * Contact: me@relativelyobjective.tech
 * Formulas from DMG p 274
 * 
 */
public class ChallengeRatingCalculator {
	public static double getChallengeRating(
		int armorClass, int hitPoints, int attackBonus, int damPerRound
		) {
		double defensiveCR = getDefensiveCR(armorClass, hitPoints);
		double offensiveCR = getOffensiveCR(attackBonus, damPerRound);
		double finalCR = (offensiveCR + defensiveCR) / 2;
		/*
		if (finalCR >= 1.0 && (finalCR / 1.0) != 0.0) {
		System.out.printf("Raw Challenge Rating: %f\n", finalCR);
			//Round up
			finalCR += 1- (finalCR / 1.0);
		} else if (finalCR < 1.0) {
			//Round decimal CRs
			if (finalCR > 0.5) {
				finalCR = 1;
			} else if (finalCR > 0.25) {
				finalCR = 0.5;
			} else if (finalCR > 0.125) {
				finalCR = 0.25;
			} else if (finalCR > (0.125 / 2)) {
				finalCR = 0.125;
			} else {
				finalCR = 0;
			}
		}
		System.out.printf("New Challenge Rating: %f\n", finalCR);
		*/
		return finalCR;
	}
	private static double getOffensiveCR(int attackBonus, int damPerRound) {
		double dpsCR = getDamagePerRoundCR(damPerRound);
		double offensiveCR = dpsCR;
		int tempAttackBonus = attackBonus;
		List<Double> attackBonusCR = getAttackBonusCR(tempAttackBonus);
		if (listContainsDouble(attackBonusCR, dpsCR)) {
			//Same CR -- all good
		}else if (dpsCR < getLowestFromList(attackBonusCR, dpsCR)) {
			//DPS CR too high
			int loopsGoneThrough = 0;
			while (!listContainsDouble(attackBonusCR, dpsCR)) {
				//Adjust loop
				tempAttackBonus -= 1;
				loopsGoneThrough++;
				attackBonusCR = getAttackBonusCR(tempAttackBonus);
			}
			for (int i = 0; i < loopsGoneThrough; i++) {
				//For every two points, increase CR
				if ((i - 1) % 2 == 0) {
					//Adjust final CR
					if (offensiveCR < 1.0) {
						offensiveCR *= 2;
					} else {
						offensiveCR += 1;
					}
				}
			}
		}else if (dpsCR > getLowestFromList(attackBonusCR, dpsCR)) {
			//DPS CR too low
			int loopsGoneThrough = 0;
			while (!listContainsDouble(attackBonusCR, dpsCR)) {
				//Adjust loop
				tempAttackBonus += 1;
				loopsGoneThrough++;
				attackBonusCR = getAttackBonusCR(tempAttackBonus);
			}
			for (int i = 0; i < loopsGoneThrough; i++) {
				//For every two points, decrease CR
				if ((i - 1) % 2 == 0) {
					//Adjust final CR
					if (offensiveCR == 1.0/8.0) {
						offensiveCR = 0;
						break;
					}if (offensiveCR <= 1.0) {
						offensiveCR /= 2;
					} else {
						offensiveCR -= 1;
					}
				}
			}
		} else {
			System.out.printf("Error calculating offensive CR\n");
		}
		//System.out.printf("Offensive Challenge Rating: %f\n", offensiveCR);
		return offensiveCR;
	}
	private static double getDefensiveCR(int armorClass, int hitPoints) {
		double hpCR = getHitPointCR(hitPoints);
		//System.out.printf("HP CR: %f\n", dpsCR);
		double defensiveCR = hpCR;
		int tempAC = armorClass;
		List<Double> acCR = getArmorCR(tempAC);
		if (listContainsDouble(acCR, hpCR)) {
			//Same CR -- all good
		}else if (hpCR < getLowestFromList(acCR, hpCR)) {
			//Armor CR too high
			//System.out.printf("AC CR is too high\n");
			int loopsGoneThrough = 0;
			while (!listContainsDouble(acCR, hpCR)) {
				//System.out.printf("AC CR: is still too high\n");
				//System.out.printf("Testing AC CR: %d\n", tempAttackBonus);
				//Adjust loop
				tempAC -= 1;
				loopsGoneThrough++;
				acCR = getArmorCR(tempAC);
			}
			for (int i = 0; i < loopsGoneThrough; i++) {
				//For every two points, increase CR
				if ((i - 1) % 2 == 0) {
					//Adjust final CR
					if (defensiveCR < 1.0) {
						defensiveCR *= 2;
					} else {
						defensiveCR += 1;
					}
				}
			}
		}else if (hpCR > getHighestFromList(acCR, hpCR)) {
			//Armor CR too low
			//System.out.printf("AC CR is too low\n");
			int loopsGoneThrough = 0;
			while (!listContainsDouble(acCR, hpCR)) {
				//System.out.printf("AC CR: is still too low\n");
				//System.out.printf("Testing AC CR: %d\n", tempAttackBonus);
				//Adjust loop
				tempAC += 1;
				loopsGoneThrough++;
				acCR = getArmorCR(tempAC);
			}
			for (int i = 0; i < loopsGoneThrough; i++) {
				//For every two points, decrease CR
				if ((i - 1) % 2 == 0) {
					//Adjust final CR
					if (defensiveCR == 1.0/8.0) {
						defensiveCR = 0;
						break;
					}if (defensiveCR <= 1.0) {
						defensiveCR /= 2;
					} else {
						defensiveCR -= 1;
					}
				}
			}
		} else {
			System.out.printf("Error calculating defensive CR\n");
		}
		//System.out.printf("Defensive Challenge Rating: %f\n", defensiveCR);
		return defensiveCR;
	}
	public static List<Double> getArmorCR(int armorClass) {
		List<Double> returnMe = new LinkedList<>();
		if (armorClass < 13) {
			returnMe.add(0.0);
		} else if (armorClass == 13) {
			returnMe.add(1.0/8.0);
			returnMe.add(1.0/4.0);
			returnMe.add(1.0/2.0);
			returnMe.add(1.0);
			returnMe.add(2.0);
			returnMe.add(3.0);
		} else if (armorClass == 14) {
			returnMe.add(4.0);
		} else if (armorClass == 15) {
			returnMe.add(5.0);
			returnMe.add(6.0);
			returnMe.add(7.0);
		} else if (armorClass == 16) {
			returnMe.add(8.0);
			returnMe.add(9.0);
		} else if (armorClass == 17) {
			returnMe.add(10.0);
			returnMe.add(11.0);
			returnMe.add(12.0);
		} else if (armorClass == 18) {
			returnMe.add(13.0);
			returnMe.add(14.0);
			returnMe.add(15.0);
			returnMe.add(16.0);
		} else if (armorClass == 19) {
			returnMe.add(17.0);
			returnMe.add(18.0);
			returnMe.add(19.0);
			returnMe.add(20.0);
			returnMe.add(21.0);
			returnMe.add(22.0);
			returnMe.add(23.0);
			returnMe.add(24.0);
			returnMe.add(25.0);
			returnMe.add(26.0);
			returnMe.add(27.0);
			returnMe.add(28.0);
			returnMe.add(29.0);
			returnMe.add(30.0);
		} else {
			//Over 19
			returnMe.add(30.0);
		}
		return returnMe;
	}
	public static double getHitPointCR(int hitPoints) {
		if (hitPoints <= 6) {
			return 0;
		} else if (hitPoints <= 35) {
			return 1.0/8.0;
		} else if (hitPoints <= 49) {
			return 1.0/4.0;
		} else if (hitPoints <= 70) {
			return 1.0/2.0;
		} else if (hitPoints <= 85) {
			return 1.0;
		} else if (hitPoints <= 100) {
			return 2.0;
		} else if (hitPoints <= 115) {
			return 3.0;
		} else if (hitPoints <= 130) {
			return 4.0;
		} else if (hitPoints <= 145) {
			return 5.0;
		} else if (hitPoints <= 160) {
			return 6.0;
		} else if (hitPoints <= 175) {
			return 7.0;
		} else if (hitPoints <= 190) {
			return 8.0;
		} else if (hitPoints <= 205) {
			return 9.0;
		} else if (hitPoints <= 220) {
			return 10.0;
		} else if (hitPoints <= 235) {
			return 11.0;
		} else if (hitPoints <= 250) {
			return 12.0;
		} else if (hitPoints <= 265) {
			return 13.0;
		} else if (hitPoints <= 280) {
			return 14.0;
		} else if (hitPoints <= 295) {
			return 15.0;
		} else if (hitPoints <= 310) {
			return 16.0;
		} else if (hitPoints <= 325) {
			return 17.0;
		} else if (hitPoints <= 340) {
			return 18.0;
		} else if (hitPoints <= 355) {
			return 19.0;
		} else if (hitPoints <= 400) {
			return 20.0;
		} else if (hitPoints <= 445) {
			return 21.0;
		} else if (hitPoints <= 490) {
			return 22.0;
		} else if (hitPoints <= 535) {
			return 23.0;
		} else if (hitPoints <= 580) {
			return 24.0;
		} else if (hitPoints <= 625) {
			return 25.0;
		} else if (hitPoints <= 670) {
			return 26.0;
		} else if (hitPoints <= 715) {
			return 27.0;
		} else if (hitPoints <= 760) {
			return 28.0;
		} else if (hitPoints <= 805) {
			return 29.0;
		} else {
			return 30.0;
		}
	}
	public static List<Double> getAttackBonusCR(int attackBonus) {
		List<Double> returnMe = new LinkedList<Double>();
		if (attackBonus < 3) {
			returnMe.add(0.0);
			return returnMe;
		}
		switch (attackBonus) {
			case 3:
				returnMe.add(1.0/8.0);
				returnMe.add(1.0/4.0);
				returnMe.add(1.0/2.0);
				returnMe.add(1.0);
				returnMe.add(2.0);
				break;
			case 4:
				returnMe.add(3.0);
				break;
			case 5:
				returnMe.add(4.0);
				break;
			case 6:
				returnMe.add(5.0);
				returnMe.add(6.0);
				returnMe.add(7.0);
				break;
			case 7:
				returnMe.add(8.0);
				returnMe.add(9.0);
				returnMe.add(10.0);
				break;
			case 8:
				returnMe.add(11.0);
				returnMe.add(12.0);
				returnMe.add(13.0);
				returnMe.add(14.0);
				returnMe.add(15.0);
				break;
			case 9:
				returnMe.add(16.0);
				break;
			case 10:
				returnMe.add(17.0);
				returnMe.add(18.0);
				returnMe.add(19.0);
				returnMe.add(20.0);
				break;
			case 11:
				returnMe.add(21.0);
				returnMe.add(22.0);
				returnMe.add(23.0);
				break;
			case 12:
				returnMe.add(24.0);
				returnMe.add(25.0);
				returnMe.add(26.0);
				break;
			case 13:
				returnMe.add(27.0);
				returnMe.add(28.0);
				returnMe.add(29.0);
				break;
			case 14:
				returnMe.add(30.0);
				break;
			default:
				//Over 14 attack bonux
				returnMe.add(30.0);
				break;
		}
		return returnMe;
	}
	public static double getDamagePerRoundCR(int damagePerRound) {
		if (damagePerRound <= 1) {
			return 0;
		} else if (damagePerRound <= 3) {
			return 1.0/8.0;
		} else if (damagePerRound <= 5) {
			return 1.0/4.0;
		} else if (damagePerRound <= 8) {
			return 1.0/2.0;
		} else if (damagePerRound <= 14) {
			return 1.0;
		} else if (damagePerRound <= 20) {
			return 2.0;
		} else if (damagePerRound <= 26) {
			return 3.0;
		} else if (damagePerRound <= 32) {
			return 4.0;
		} else if (damagePerRound <= 38) {
			return 5.0;
		} else if (damagePerRound <= 44) {
			return 6.0;
		} else if (damagePerRound <= 50) {
			return 7.0;
		} else if (damagePerRound <= 56) {
			return 8.0;
		} else if (damagePerRound <= 62) {
			return 9.0;
		} else if (damagePerRound <= 68) {
			return 10.0;
		} else if (damagePerRound <= 74) {
			return 11.0;
		} else if (damagePerRound <= 80) {
			return 12.0;
		} else if (damagePerRound <= 86) {
			return 13.0;
		} else if (damagePerRound <= 92) {
			return 14.0;
		} else if (damagePerRound <= 98) {
			return 15.0;
		} else if (damagePerRound <= 104) {
			return 16.0;
		} else if (damagePerRound <= 110) {
			return 17.0;
		} else if (damagePerRound <= 116) {
			return 18.0;
		} else if (damagePerRound <= 122) {
			return 19.0;
		} else if (damagePerRound <= 140) {
			return 20.0;
		} else if (damagePerRound <= 158) {
			return 21.0;
		} else if (damagePerRound <= 176) {
			return 22.0;
		} else if (damagePerRound <= 194) {
			return 23.0;
		} else if (damagePerRound <= 212) {
			return 24.0;
		} else if (damagePerRound <= 230) {
			return 25.0;
		} else if (damagePerRound <= 248) {
			return 26.0;
		} else if (damagePerRound <= 266) {
			return 27.0;
		} else if (damagePerRound <= 284) {
			return 28.0;
		} else if (damagePerRound <= 302) {
			return 29.0;
		} else {
			return 30.0;
		}
	}
	public static List<Double> getSaveDCCR (int saveDC) {
		List<Double> returnMe = new LinkedList<>();
		if (saveDC < 13) {
			returnMe.add(0.0);
		} else if (saveDC == 13) {
			returnMe.add(1.0/8.0);
			returnMe.add(1.0/4.0);
			returnMe.add(1.0/2.0);
			returnMe.add(1.0);
			returnMe.add(2.0);
			returnMe.add(3.0);
		} else if (saveDC == 14) {
			returnMe.add(4.0);
		} else if (saveDC == 15) {
			returnMe.add(5.0);
			returnMe.add(6.0);
			returnMe.add(7.0);
		} else if (saveDC == 16) {
			returnMe.add(8.0);
			returnMe.add(9.0);
			returnMe.add(10.0);
		} else if (saveDC == 17) {
			returnMe.add(11.0);
			returnMe.add(12.0);
		} else if (saveDC == 18) {
			returnMe.add(13.0);
			returnMe.add(14.0);
			returnMe.add(15.0);
			returnMe.add(16.0);
		} else if (saveDC == 19) {
			returnMe.add(17.0);
			returnMe.add(18.0);
			returnMe.add(19.0);
			returnMe.add(20.0);
		} else if (saveDC == 20) {
			returnMe.add(21.0);
			returnMe.add(22.0);
			returnMe.add(23.0);
		} else if (saveDC == 21) {
			returnMe.add(24.0);
			returnMe.add(25.0);
			returnMe.add(26.0);
		} else if (saveDC == 22) {
			returnMe.add(27.0);
			returnMe.add(28.0);
			returnMe.add(29.0);
		} else {
			returnMe.add(30.0);
		}
		return returnMe;
	}
	public static boolean listContainsDouble(List<Double> list, double value) {
		for (Double d : list) {
			if (d.doubleValue() == value) {
				return true;
			}
		}
		return false;
	}
	private static double getLowestFromList(List<Double> list, double value) {
		Double lowest = null;
		for (Double d : list) {
			if (lowest == null) {
				lowest = d;
			} else if (d < lowest) {
				lowest = d;
			}
		}
		return lowest;
	}
	private static double getHighestFromList(List<Double> list, double value) {
		Double highest = null;
		for (Double d : list) {
			if (highest == null) {
				highest = d;
			} else if (d > highest) {
				highest = d;
			}
		}
		return highest;
	}
	private static int getAverageFromList(List<Integer> list) {
		if (list.isEmpty()) {
			return 0;
		}
		int runningTotal = 0;
		for (Integer i : list) {
			runningTotal += i;
		}
		runningTotal /= list.size();
		return runningTotal;
	}
	public static int guessAttackBonus() {
		List<Integer> attackBonuses = new LinkedList<>();
		for (Spellcaster s : MonsterInformation.getSpellcaster()) {
			attackBonuses.add(s.getToHit());
		}
		for (Action a : MonsterInformation.getActions()) {
			attackBonuses.add(a.getToHit());
		}
		return getAverageFromList(attackBonuses);
	}
	public static int guessDamagePerRound() {
		List<Integer> damagePerRound = new LinkedList<>();
		for (Action a : MonsterInformation.getActions()) {
			try {
				damagePerRound.add(a.getDiceCount() * Integer.parseInt(a.getDiceType().replace("d", "")));
			} catch (NumberFormatException e) {
				System.out.printf("Could not parse number: %s", a.getDiceType().replace("d", ""));
			}
		}
		return getAverageFromList(damagePerRound);
	}
}