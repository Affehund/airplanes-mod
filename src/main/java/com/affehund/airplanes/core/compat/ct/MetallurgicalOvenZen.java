package com.affehund.airplanes.core.compat.ct;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

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
@ZenClass("affehund.airplanes.MetallurgicalOven")
public class MetallurgicalOvenZen
{
	@ZenMethod
	public static void addRecipe(IItemStack output, IIngredient first, IIngredient second, int time)
	{

	}

	@ZenMethod
	public static void removeRecipe(IItemStack output)
	{

	}

	@ZenMethod
	public static void removeAll()
	{

	}
}
