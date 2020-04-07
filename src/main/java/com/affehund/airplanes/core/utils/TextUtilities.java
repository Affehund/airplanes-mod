package com.affehund.airplanes.core.utils;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;

/**
 * @author Affehund
 * 
 *         MIT License Copyright (c) 2020 Affehund Dev Team
 * 
 *         Permission is hereby granted, free of charge, to any person obtaining
 *         a copy of this software and associated documentation files (the
 *         "Software"), to deal in the Software without restriction, including
 *         without limitation the rights to use, copy, modify, merge, publish,
 *         distribute, sublicense, and/or sell copies of the Software, and to
 *         permit persons to whom the Software is furnished to do so, subject to
 *         the following conditions:
 * 
 *         The above copyright notice and this permission notice shall be
 *         included in all copies or substantial portions of the Software.
 * 
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *         EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *         MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *         NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *         BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *         ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *         CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */
public class TextUtilities
{
	private static final Pattern COMPILE = Pattern.compile("@", Pattern.LITERAL);
	
	public static String stringToRainbow(String parString, boolean parReturnToBlack)
	{
		int stringLength = parString.length();
		if (stringLength < 1)
		{
			return "";
		}
		String outputString = "";
		TextFormatting[] colorChar =
		{ TextFormatting.RED, TextFormatting.GOLD, TextFormatting.YELLOW, TextFormatting.GREEN, TextFormatting.AQUA,
				TextFormatting.BLUE, TextFormatting.LIGHT_PURPLE, TextFormatting.DARK_PURPLE };
		for (int i = 0; i < stringLength; i++)
		{
			outputString = outputString + colorChar[i % 8] + parString.substring(i, i + 1);
		}
		if (parReturnToBlack)
		{
			return outputString + TextFormatting.BLACK;
		}
		return outputString + TextFormatting.WHITE;
	}

	public static String stringToRainbow(String parString)
	{
		return stringToRainbow(parString, false);
	}
	
	public static void addInformationLocalizedForEnergyAndFluid(List<String> tooltip, String key, Object... parameters)
	{
		String translated = I18n.format(key, parameters);
		translated = COMPILE.matcher(translated).replaceAll("\u00a7");
		Collections.addAll(tooltip, StringUtils.split(translated, "\n"));
	}
}
