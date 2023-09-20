package linear;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
  GaussianEliminationStructureTest.class
  ,GaussianEliminationTest.class
})
public class GaussianEliminationTestSuite {}
