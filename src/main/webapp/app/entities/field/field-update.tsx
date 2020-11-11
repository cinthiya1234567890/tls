import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICascaderParam } from 'app/shared/model/cascader-param.model';
import { getEntities as getCascaderParams } from 'app/entities/cascader-param/cascader-param.reducer';
import { ICascader } from 'app/shared/model/cascader.model';
import { getEntities as getCascaders } from 'app/entities/cascader/cascader.reducer';
import { getEntity, updateEntity, createEntity, reset } from './field.reducer';
import { IField } from 'app/shared/model/field.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFieldUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FieldUpdate = (props: IFieldUpdateProps) => {
  const [dataSourceId, setDataSourceId] = useState('0');
  const [dataTypeId, setDataTypeId] = useState('0');
  const [cascaderId, setCascaderId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { fieldEntity, cascaderParams, cascaders, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/field');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCascaderParams();
    props.getCascaders();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...fieldEntity,
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
          <h2 id="matriSearchApp.field.home.createOrEditLabel">Create or edit a Field</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : fieldEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="field-id">ID</Label>
                  <AvInput id="field-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="fieldNameLabel" for="field-fieldName">
                  Field Name
                </Label>
                <AvField id="field-fieldName" type="text" name="fieldName" />
              </AvGroup>
              <AvGroup>
                <Label id="peckOrderLabel" for="field-peckOrder">
                  Peck Order
                </Label>
                <AvField id="field-peckOrder" type="string" className="form-control" name="peckOrder" />
              </AvGroup>
              <AvGroup>
                <Label for="field-dataSource">Data Source</Label>
                <AvInput id="field-dataSource" type="select" className="form-control" name="dataSourceId">
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
              <AvGroup>
                <Label for="field-dataType">Data Type</Label>
                <AvInput id="field-dataType" type="select" className="form-control" name="dataTypeId">
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
              <AvGroup>
                <Label for="field-cascader">Cascader</Label>
                <AvInput id="field-cascader" type="select" className="form-control" name="cascaderId">
                  <option value="" key="0" />
                  {cascaders
                    ? cascaders.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.cascaderName}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/field" replace color="info">
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
  cascaderParams: storeState.cascaderParam.entities,
  cascaders: storeState.cascader.entities,
  fieldEntity: storeState.field.entity,
  loading: storeState.field.loading,
  updating: storeState.field.updating,
  updateSuccess: storeState.field.updateSuccess,
});

const mapDispatchToProps = {
  getCascaderParams,
  getCascaders,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FieldUpdate);
