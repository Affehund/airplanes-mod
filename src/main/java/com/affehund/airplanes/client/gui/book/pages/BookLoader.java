package com.affehund.airplanes.client.gui.book.pages;

import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.affehund.airplanes.AirplanesMod;
import com.affehund.airplanes.client.gui.book.GuiBookOfAirplanes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

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
public class BookLoader
{
	public static BookLoader INSTANCE;
	public static int leftSideOffsetX = (GuiBookOfAirplanes.BOOK_WIDTH / 2) + 12;
	private DefaultPage mainPage;

	/*
	 * get instance
	 */
	public BookLoader()
	{
		if (INSTANCE != null)
		{
			AirplanesMod.log.info("Building new book instance");
			INSTANCE = this;
		}
		AirplanesMod.log.info("Building book instance");
		INSTANCE = this;
	}

	/*
	 * load main page
	 */
	public void load()
	{
		AirplanesMod.log.info("Building book main page");
		this.mainPage = this.parsePages(this.parseFile("assets/airplanes/book/main.json"))[0];
	}
	
	/*
	 * get page type and json inputs
	 */
	public DefaultPage[] parsePages(JSONObject pageObject)
	{
		if (!pageObject.containsKey("type"))
		{
			throw new IllegalArgumentException("Invalid JSONObject \'" + pageObject + "\'");
		}
		String type = (String) pageObject.get("type");
		switch (type)
		{

		case "mainPage":
			String bookName = (String) pageObject.get("bookName");
			String edition = (String) pageObject.get("edition");
			String color = (String) pageObject.get("color");
			String text = (String) pageObject.get("text");
			String categoryTitle = (String) pageObject.get("categoryTitle");
			JSONObject[] categories = this.parseJSONArray((JSONArray) pageObject.get("categories"));
			return new DefaultPage[]
			{ new MainPage(bookName, edition, color, text, categoryTitle, this.parseListPageItems(categories)) };

		case "pages":
			JSONObject[] pages = this.parseJSONArray((JSONArray) pageObject.get("pages"));
			return this.parsePages(pages);

		case "textPage":
			String pageTitle = (String) pageObject.get("title");
			String pageText = (String) pageObject.get("text");
			String pageTitle2 = "";
			if (pageObject.containsKey("title2"))
			{
				pageTitle2 = (String) pageObject.get("title2");
			}
			String pageText2 = "";
			if (pageObject.containsKey("text2"))
			{
				pageText2 = (String) pageObject.get("text2");
			}
			return new DefaultPage[]
			{ new TextPage(pageTitle, pageText, pageTitle2, pageText2) };

		case "recipePage":
			if(Item.REGISTRY.getObject(new ResourceLocation((String) pageObject.get("item"))) == null) {
				throw new IllegalArgumentException("Unknown item for \'" + type + "\'");
			}
			Item item = Item.REGISTRY.getObject(new ResourceLocation((String) pageObject.get("item")));
			String description = (String) pageObject.get("description");
			RecipePage.RecipeType recipeType = RecipePage.RecipeType.valueOf((String) pageObject.get("recipeType"));
			return new DefaultPage[]
			{ new RecipePage(item, description, recipeType) };

		default:
			throw new IllegalArgumentException("Unknown page type \'" + type + "\'");
		}
	}

	/*
	 * parse json files
	 */
	public JSONObject parseFile(String fileLocation)
	{
		JSONParser parser = new JSONParser();
		ClassLoader classLoader = this.getClass().getClassLoader();
		try
		{
			return (JSONObject) parser.parse(new InputStreamReader(classLoader.getResourceAsStream(fileLocation)));
		} catch (IOException | ParseException e)
		{
			return null;
		}
	}

	/*
	 * parse json arrays
	 */
	public JSONObject[] parseJSONArray(JSONArray jsonArray)
	{
		JSONObject[] jsonObjects = new JSONObject[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++)
		{
			jsonObjects[i] = (JSONObject) jsonArray.get(i);
		}
		return jsonObjects;
	}

	/*
	 * parse chapter pages
	 */
	public ChapterPage[] parseListPageItems(JSONObject[] pageArray)
	{
		ChapterPage[] pages = new ChapterPage[pageArray.length];
		for (int i = 0; i < pageArray.length; i++)
		{
			String name = (String) pageArray[i].get("name");
			ItemStack icon = new ItemStack(
					Item.REGISTRY.getObject(new ResourceLocation((String) pageArray[i].get("icon"))));
			String id = (String) pageArray[i].get("id");
			pages[i] = new ChapterPage(name, id, icon);
		}
		return pages;
	}

	/*
	 * parse default page
	 */
	public DefaultPage[] parsePages(JSONObject[] pageArray)
	{
		DefaultPage[] pages = new DefaultPage[pageArray.length];
		for (int i = 0; i < pageArray.length; i++)
		{
			pages[i] = this.parsePages(pageArray[i])[0];
		}
		return pages;
	}

	/*
	 * get main page
	 */
	public DefaultPage getMainPage()
	{
		return mainPage;
	}
}
