/*
 * Copyright 2017-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.distributed.build_slave;

import com.facebook.buck.distributed.thrift.StampedeId;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/** Snapshot of coordinator rule execution history. Generated by {@link DistBuildTraceTracker}. */
public class DistBuildTrace {

  /** Single build rule information. */
  public static class RuleTrace {
    public final String ruleName;
    public final long startEpochMillis;
    public final long finishEpochMillis;

    public RuleTrace(String ruleName, long startEpochMillis, long finishEpochMillis) {
      this.ruleName = ruleName;
      this.startEpochMillis = startEpochMillis;
      this.finishEpochMillis = finishEpochMillis;
    }
  }

  public final StampedeId stampedeId;
  public final Map<String, List<RuleTrace>> rulesByMinionId;

  public DistBuildTrace(StampedeId stampedeId, Map<String, List<RuleTrace>> rulesByMinionId) {
    this.stampedeId = stampedeId;
    this.rulesByMinionId = rulesByMinionId;
  }

  /** Write trace in chrome trace format. */
  public void dumpToChromeTrace(Path chromeTrace) throws IOException {
    DistBuildChromeTraceRenderer.render(this, chromeTrace);
  }
}
