package ru.alvioneurope.ui.suites;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.alvioneurope.ui.tests.OrdersPageTest;

/**
 * Created by aburnasov on 10/5/2015.
 */
@RunWith(Categories.class)
@Categories.IncludeCategory(qa.categories.priority.Smoke.class)
@Suite.SuiteClasses(OrdersPageTest.class)
public class Smoke_Suite {}
