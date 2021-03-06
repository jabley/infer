/*
 * Copyright (c) 2015 - present Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package endtoend.java.eradicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static utils.matchers.ResultContainsNoErrorInMethod.doesNotContain;
import static utils.matchers.ResultContainsExactly.containsExactly;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import utils.InferException;
import utils.InferResults;

public class SuppressWarningsTest {

  public static final String SOURCE_FILE =
      "infer/tests/codetoanalyze/java/eradicate/SuppressWarningsExample.java";

  public static final String NULL_METHOD_CALL = "ERADICATE_NULL_METHOD_CALL";

  private static InferResults inferResults;

  @BeforeClass
  public static void loadResults() throws InterruptedException, IOException {
    inferResults = InferResults.loadEradicateResults(
        SuppressWarningsTest.class,
        SOURCE_FILE);
  }

  @Test
  public void matchErrors()
      throws IOException, InterruptedException, InferException {
    String[] methods = {
      "doNotSuppressWrongAnnot",
      "doNotSuppressNoAnnot"
    };
    assertThat(
        "Results should contain " + NULL_METHOD_CALL,
        inferResults,
        containsExactly(
            NULL_METHOD_CALL,
            SOURCE_FILE,
            methods));
  }

}
