/**
 *
 * Copyright (c) 2006-2017, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.tool.config;

import com.speedment.runtime.config.Index;
import com.speedment.runtime.config.Table;
import com.speedment.tool.config.component.DocumentPropertyComponent;
import com.speedment.tool.config.mutator.DocumentPropertyMutator;
import com.speedment.tool.config.mutator.IndexPropertyMutator;
import com.speedment.tool.config.trait.HasEnabledProperty;
import com.speedment.tool.config.trait.HasExpandedProperty;
import com.speedment.tool.config.trait.HasNameProperty;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Stream;

import static com.speedment.runtime.core.internal.util.ImmutableListUtil.concat;

/**
 *
 * @author  Emil Forslund
 * @since   2.3.0
 */
public final class IndexProperty 
    extends AbstractChildDocumentProperty<Table, IndexProperty>
    implements Index, 
        HasEnabledProperty, 
        HasExpandedProperty, 
        HasNameProperty {

    public IndexProperty(Table parent) {
        super(parent);
    }

    public BooleanProperty uniqueProperty() {
        return booleanPropertyOf(UNIQUE, Index.super::isUnique);
    }

    @Override
    public boolean isUnique() {
        return uniqueProperty().get();
    }

    @Override
    public Stream<IndexColumnProperty> indexColumns() {
        return indexColumnsProperty().stream();
    }

    public ObservableList<IndexColumnProperty> indexColumnsProperty() {
        return observableListOf(INDEX_COLUMNS);
    }

    @Override
    public boolean isExpandedByDefault() {
        return false;
    }

    @Override
    public IndexPropertyMutator mutator() {
        return DocumentPropertyMutator.of(this);
    }

    @Override
    protected List<String> keyPathEndingWith(String key) {
        return concat(DocumentPropertyComponent.INDEXES, key);
    }
}
