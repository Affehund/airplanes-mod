package com.affehund.airplanes;

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
public class Reference
{
	public static final String MODID = "airplanes";
	public static final String NAME = "Airplanes Mod";
	public static final String VERSION = "airplanes_a0.8.jar";
	public static final String MC_VERSION = "1.12.2";
	public static final String UPDATEJSON = "https://github.com/Affehund/airplanes-mod/blob/master/update.json";

	public static final String CLIENT = "com.affehund.airplanes.proxy.ClientProxy";
	public static final String COMMON = "com.affehund.airplanes.proxy.CommonProxy";

	static int gui_id = 0;
	public static final int GUI_COMBUSTION_ENGINE = gui_id++;
	public static final int GUI_SUITCASE = gui_id++;
	public static final int GUI_BOOK = gui_id++;
	public static final int GUI_ENERGY_STORAGE = gui_id++;
	public static final int GUI_FLUID_TANK = gui_id++;
	public static final int GUI_METALLURGICAL_OVEN = gui_id++;
	public static final int GUI_MAGICAL_COMBINER = gui_id++;

	static int entity_id = 0;
	public static final int AIRPLANE_BOEING737 = entity_id++;
	public static final int AIRPLANE_CESSNA172 = entity_id++;

	public static final int CALVINTOCELSIUS = 273;
}
