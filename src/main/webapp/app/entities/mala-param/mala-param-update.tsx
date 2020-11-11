import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IMala } from 'app/shared/model/mala.model';
import { getEntities as getMalas } from 'app/entities/mala/mala.reducer';
import { IField } from 'app/shared/model/field.model';
import { getEntities as getFields } from 'app/entities/field/field.reducer';
import { getEntity, updateEntity, createEntity, reset } from './mala-param.reducer';
import { IMalaParam } from 'app/shared/model/mala-param.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMalaParamUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MalaParamUpdate = (props: IMalaParamUpdateProps) => {
  const [malaId, setMalaId] = useState('0');
  const [fieldId, setFieldId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { malaParamEntity, malas, fields, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/mala-param');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getMalas();
    props.getFields();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...malaParamEntity,
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
          <h2 id="matriSearchApp.malaParam.home.createOrEditLabel">Create or edit a MalaParam</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : malaParamEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="mala-param-id">ID</Label>
                  <AvInput id="mala-param-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="peckOrderLabel" for="mala-param-peckOrder">
                  Peck Order
                </Label>
                <AvField id="mala-param-peckOrder" type="string" className="form-control" name="peckOrder" />
              </AvGroup>
              <AvGroup>
                <Label for="mala-param-mala">Mala</Label>
                <AvInput id="mala-param-mala" type="select" className="form-control" name="malaId">
                  <option value="" key="0" />
                  {malas
                    ? malas.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.malaName}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="mala-param-field">Field</Label>
                <AvInput id="mala-param-field" type="select" className="form-control" name="fieldId">
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
              <Button tag={Link} id="cancel-save" to="/mala-param" replace color="info">
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
  malas: storeState.mala.entities,
  fields: storeState.field.entities,
  malaParamEntity: storeState.malaParam.entity,
  loading: storeState.malaParam.loading,
  updating: storeState.malaParam.updating,
  updateSuccess: storeState.malaParam.updateSuccess,
});

const mapDispatchToProps = {
  getMalas,
  getFields,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MalaParamUpdate);
