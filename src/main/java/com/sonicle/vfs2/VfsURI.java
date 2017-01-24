/* 
 * Copyright (C) 2014 Sonicle S.r.l.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License version 3 as published by
 * the Free Software Foundation with the addition of the following permission
 * added to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED
 * WORK IN WHICH THE COPYRIGHT IS OWNED BY SONICLE, SONICLE DISCLAIMS THE
 * WARRANTY OF NON INFRINGEMENT OF THIRD PARTY RIGHTS.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses or write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301 USA.
 *
 * You can contact Sonicle S.r.l. at email address sonicle[at]sonicle.com
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License version 3.
 *
 * In accordance with Section 7(b) of the GNU Affero General Public License
 * version 3, these Appropriate Legal Notices must retain the display of the
 * Sonicle logo and Sonicle copyright notice. If the display of the logo is not
 * reasonably feasible for technical reasons, the Appropriate Legal Notices must
 * display the words "Copyright (C) 2014 Sonicle S.r.l.".
 */
package com.sonicle.vfs2;

import com.sonicle.commons.PathUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author malbinola
 */
public class VfsURI {
	
	private VfsURI() {}
	
	public static class Builder<T extends Builder> {
		private String scheme = null;
		private String host = null;
		private Integer port = null;
		private String username = null;
		private String password = null;
		private String path = null;
		
		public Builder() {}

		public T scheme(String scheme) {
			this.scheme = scheme;
			return (T)this;
		}

		public T host(String host) {
			this.host = host;
			return (T)this;
		}

		public T port(Integer port) {
			this.port = port;
			return (T)this;
		}

		public T username(String username) {
			this.username = username;
			return (T)this;
		}

		public T password(String password) {
			this.password = password;
			return (T)this;
		}

		public T path(String path) {
			this.path = path;
			return (T)this;
		}
		
		public URI build() throws URISyntaxException {
			String shost = StringUtils.defaultIfBlank(this.host, null);
			int iport = (this.port == null) ? -1 : this.port;
			String suserInfo = "";
			if (!StringUtils.isBlank(username)) {
				suserInfo += username;
				if (!StringUtils.isBlank(password)) {
					suserInfo += ":";
					suserInfo += password;
				}
			}
			String spath = PathUtils.ensureTrailingSeparator(this.path, true);
			return new URI(this.scheme, suserInfo, shost, iport, spath, null, null);
		}
	}
}
