/*
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package pyspark_cassandra.readers;

import java.util.ArrayList;
import java.util.List;

import pyspark_cassandra.types.LWRow;

import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.Row;
import com.datastax.spark.connector.cql.TableDef;

public class KVRowsReaderFactory extends KVRowReader<LWRow> {
	private static final long serialVersionUID = 1L;

	@Override
	protected LWRow parse(Row row, List<String> columnNames, TableDef tableDef, ProtocolVersion protocol) {
		List<Object> values = new ArrayList<Object>(columnNames.size());

		for (String column : columnNames) {
			values.add(readColumn(column, row, protocol));
		}

		return new LWRow(columnNames, values);
	}
}
