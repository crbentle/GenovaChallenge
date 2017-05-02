package com.genova.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.genova.data.CsvWriterTest;
import com.genova.data.TabFileReaderTest;
import com.genova.properties.manager.PropertyManagerTest;
import com.genova.sorter.PersonComparatorTest;

/**
 * @author Chris Bentley
 *
 */
@RunWith(Suite.class)
@SuiteClasses({CsvWriterTest.class, TabFileReaderTest.class, PropertyManagerTest.class, PersonComparatorTest.class})
public class AllTests {

}
