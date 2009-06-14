//  Copyright 2008 The Apache Software Foundation
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.apache.tapestry5.ioc.internal;

import org.apache.tapestry5.ioc.OperationTracker;
import org.apache.tapestry5.ioc.internal.util.Invokable;

/**
 * Minimal implementation used for testing, that does no logging, tracking, or exception catching.
 */
public class QuietOperationTracker implements OperationTracker
{
    public void run(String description, Runnable operation)
    {
        operation.run();
    }

    public <T> T invoke(String description, Invokable<T> operation)
    {
        return operation.invoke();
    }
}
