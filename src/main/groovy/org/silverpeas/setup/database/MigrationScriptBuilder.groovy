/*
    Copyright (C) 2000 - 2015 Silverpeas

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    As a special exception to the terms and conditions of version 3.0 of
    the GPL, you may redistribute this Program in connection with Free/Libre
    Open Source Software ("FLOSS") applications as described in Silverpeas's
    FLOSS exception.  You should have recieved a copy of the text describing
    the FLOSS exception, and it is also available here:
    "http://www.silverpeas.org/docs/core/legal/floss_exception.html"

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.silverpeas.setup.database

/**
 *
 * @author mmoquillon
 */
class MigrationScriptBuilder {

  enum ScriptType {
    sql,
    groovy
  }

  private String scriptPath
  private ScriptType scriptType

  static MigrationScriptBuilder fromScript(String scriptPath) {
    MigrationScriptBuilder builder = new MigrationScriptBuilder()
    builder.scriptPath = scriptPath
    return builder
  }

  MigrationScriptBuilder ofType(ScriptType type) {
    this.scriptType = type
    return this
  }

  MigrationScript build() {
    MigrationScript script
    switch (scriptType) {
      case ScriptType.sql:
        script = new SQLScript(scriptPath)
        break
      case ScriptType.groovy:
        script = new GroovyScript(scriptPath)
        break
      default:
        throw new IllegalArgumentException("Unknow script type: ${scriptType}")
    }
    return script
  }
}
