/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.socket.adapter.standard;

import java.util.ArrayList;
import java.util.List;
import javax.websocket.Extension;

import org.springframework.web.socket.WebSocketExtension;

/**
 * @author Rossen Stoyanchev
 * @since 4.0
 */
public class WebSocketToStandardExtensionAdapter implements Extension {

	private final String name;

	private final List<Parameter> parameters = new ArrayList<Parameter>();

	public WebSocketToStandardExtensionAdapter(final WebSocketExtension ext) {
		this.name = ext.getName();
		for (final String paramName : ext.getParameters().keySet()) {
			this.parameters.add(new Parameter() {
				@Override
				public String getName() {
					return paramName;
				}
				@Override
				public String getValue() {
					return ext.getParameters().get(paramName);
				}
			});
		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<Parameter> getParameters() {
		return this.parameters;
	}

}