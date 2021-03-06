package com.endreman0.endermechanics.util;

import org.apache.logging.log4j.Level;

import com.endreman0.endermechanics.EnderMechanics;

import cpw.mods.fml.common.FMLLog;

public class Log{
	public static void log(Level level, Object object){
		FMLLog.log(Utility.MOD_ID, level, String.valueOf(object));
	}
	public static void all(Object object){log(Level.ALL, object);}
	public static void debug(Object object){log(Level.DEBUG, object);}
	public static void error(Object object){log(Level.ERROR, object);}
	public static void fatal(Object object){log(Level.FATAL, object);}
	public static void info(Object object){log(Level.INFO, object);}
	public static void off(Object object){log(Level.OFF, object);}
	public static void trace(Object object){log(Level.TRACE, object);}
	public static void warn(Object object){log(Level.WARN, object);}
}
