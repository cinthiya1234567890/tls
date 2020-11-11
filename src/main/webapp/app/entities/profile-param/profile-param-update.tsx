import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IProfile } from 'app/shared/model/profile.model';
import { getEntities as getProfiles } from 'app/entities/profile/profile.reducer';
import { IField } from 'app/shared/model/field.model';
import { getEntities as getFields } from 'app/entities/field/field.reducer';
import { ICascaderParam } from 'app/shared/model/cascader-param.model';
import { getEntities as getCascaderParams } from 'app/entities/cascader-param/cascader-param.reducer';
import { getEntity, updateEntity, createEntity, reset } from './profile-param.reducer';
import { IProfileParam } from 'app/shared/model/profile-param.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProfileParamUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProfileParamUpdate = (props: IProfileParamUpdateProps) => {
  const [profileId, setProfileId] = useState('0');
  const [fieldId, setFieldId] = useState('0');
  const [cascaderParamId, setCascaderParamId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { profileParamEntity, profiles, fields, cascaderParams, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/profile-param');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getProfiles();
    props.getFields();
    props.getCascaderParams();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...profileParamEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="matriSearchApp.profileParam.home.createOrEditLabel">Create or edit a ProfileParam</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : profileParamEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="profile-param-id">ID</Label>
                  <AvInput id="profile-param-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dataValueLabel" for="profile-param-dataValue">
                  Data Value
                </Label>
                <AvField id="profile-param-dataValue" type="text" name="dataValue" />
              </AvGroup>
              <AvGroup>
                <Label id="dataIntLabel" for="profile-param-dataInt">
                  Data Int
                </Label>
                <AvField id="profile-param-dataInt" type="string" className="form-control" name="dataInt" />
              </AvGroup>
              <AvGroup>
                <Label id="userEnteredCustomValueLabel" for="profile-param-userEnteredCustomValue">
                  User Entered Custom Value
                </Label>
                <AvField id="profile-param-userEnteredCustomValue" type="text" name="userEnteredCustomValue" />
              </AvGroup>
              <AvGroup>
                <Label for="profile-param-profile">Profile</Label>
                <AvInput id="profile-param-profile" type="select" className="form-control" name="profileId">
                  <option value="" key="0" />
                  {profiles
                    ? profiles.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.name}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="profile-param-field">Field</Label>
                <AvInput id="profile-param-field" type="select" className="form-control" name="fieldId">
                  <option value="" key="0" />
                  {fields
                    ? fields.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.fieldName}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="profile-param-cascaderParam">Cascader Param</Label>
                <AvInput id="profile-param-cascaderParam" type="select" className="form-control" name="cascaderParamId">
                  <option value="" key="0" />
                  {cascaderParams
                    ? cascaderParams.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.paramTitle}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/profile-param" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  profiles: storeState.profile.entities,
  fields: storeState.field.entities,
  cascaderParams: storeState.cascaderParam.entities,
  profileParamEntity: storeState.profileParam.entity,
  loading: storeState.profileParam.loading,
  updating: storeState.profileParam.updating,
  updateSuccess: storeState.profileParam.updateSuccess,
});

const mapDispatchToProps = {
  getProfiles,
  getFields,
  getCascaderParams,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProfileParamUpdate);
