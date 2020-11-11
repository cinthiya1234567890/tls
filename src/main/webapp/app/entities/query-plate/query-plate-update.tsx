import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IQuery } from 'app/shared/model/query.model';
import { getEntities as getQueries } from 'app/entities/query/query.reducer';
import { IField } from 'app/shared/model/field.model';
import { getEntities as getFields } from 'app/entities/field/field.reducer';
import { getEntity, updateEntity, createEntity, reset } from './query-plate.reducer';
import { IQueryPlate } from 'app/shared/model/query-plate.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IQueryPlateUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const QueryPlateUpdate = (props: IQueryPlateUpdateProps) => {
  const [queryId, setQueryId] = useState('0');
  const [fieldId, setFieldId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { queryPlateEntity, queries, fields, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/query-plate');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getQueries();
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
        ...queryPlateEntity,
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
          <h2 id="matriSearchApp.queryPlate.home.createOrEditLabel">Create or edit a QueryPlate</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : queryPlateEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="query-plate-id">ID</Label>
                  <AvInput id="query-plate-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup check>
                <Label id="isRangeLabel">
                  <AvInput id="query-plate-isRange" type="checkbox" className="form-check-input" name="isRange" />
                  Is Range
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="isMultiLabel">
                  <AvInput id="query-plate-isMulti" type="checkbox" className="form-check-input" name="isMulti" />
                  Is Multi
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="peckOrderLabel" for="query-plate-peckOrder">
                  Peck Order
                </Label>
                <AvField id="query-plate-peckOrder" type="string" className="form-control" name="peckOrder" />
              </AvGroup>
              <AvGroup>
                <Label for="query-plate-query">Query</Label>
                <AvInput id="query-plate-query" type="select" className="form-control" name="queryId">
                  <option value="" key="0" />
                  {queries
                    ? queries.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.queryName}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="query-plate-field">Field</Label>
                <AvInput id="query-plate-field" type="select" className="form-control" name="fieldId">
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
              <Button tag={Link} id="cancel-save" to="/query-plate" replace color="info">
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
  queries: storeState.query.entities,
  fields: storeState.field.entities,
  queryPlateEntity: storeState.queryPlate.entity,
  loading: storeState.queryPlate.loading,
  updating: storeState.queryPlate.updating,
  updateSuccess: storeState.queryPlate.updateSuccess,
});

const mapDispatchToProps = {
  getQueries,
  getFields,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(QueryPlateUpdate);
