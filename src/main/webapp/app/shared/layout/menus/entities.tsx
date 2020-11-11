import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name="Entities" id="entity-menu" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    <MenuItem icon="asterisk" to="/profile">
      Profile
    </MenuItem>
    <MenuItem icon="asterisk" to="/field">
      Field
    </MenuItem>
    <MenuItem icon="asterisk" to="/profile-param">
      Profile Param
    </MenuItem>
    <MenuItem icon="asterisk" to="/photo">
      Photo
    </MenuItem>
    <MenuItem icon="asterisk" to="/contact">
      Contact
    </MenuItem>
    <MenuItem icon="asterisk" to="/cascader">
      Cascader
    </MenuItem>
    <MenuItem icon="asterisk" to="/cascader-param">
      Cascader Param
    </MenuItem>
    <MenuItem icon="asterisk" to="/query">
      Query
    </MenuItem>
    <MenuItem icon="asterisk" to="/query-plate">
      Query Plate
    </MenuItem>
    <MenuItem icon="asterisk" to="/plate-param">
      Plate Param
    </MenuItem>
    <MenuItem icon="asterisk" to="/mala">
      Mala
    </MenuItem>
    <MenuItem icon="asterisk" to="/mala-param">
      Mala Param
    </MenuItem>
    <MenuItem icon="asterisk" to="/payment">
      Payment
    </MenuItem>
    <MenuItem icon="asterisk" to="/issue">
      Issue
    </MenuItem>
    <MenuItem icon="asterisk" to="/vital">
      Vital
    </MenuItem>
    <MenuItem icon="asterisk" to="/field-attribute">
      Field Attribute
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
