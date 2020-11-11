import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IQueryPlate } from 'app/shared/model/query-plate.model';
import { getEntities as getQueryPlates } from 'app/entities/query-plate/query-plate.reducer';
import { ICascader } from 'app/shared/model/cascader.model';
import { getEntities as getCascaders } from 'app/entities/cascader/cascader.reducer';
import { getEntity, updateEntity, createEntity, reset } from './plate-param.reducer';
import { IPlateParam } from 'app/shared/model/plate-param.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPlateParamUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PlateParamUpdate = (props: IPlateParamUpdateProps) => {
  const [queryPlateId, setQueryPlateId] = useState('0');
  const [cascaderId, setCascaderId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { plateParamEntity, queryPlates, cascaders, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/plate-param');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getQueryPlates();
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
        ...plateParamEntity,
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
          <h2 id="matriSearchApp.plateParam.home.createOrEditLabel">Create or edit a PlateParam</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : plateParamEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="plate-param-id">ID</Label>
                  <AvInput id="plate-param-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="valueLabel" for="plate-param-value">
                  Value
                </Label>
                <AvField id="plate-param-value" type="text" name="value" />
              </AvGroup>
              <AvGroup>
                <Label id="peckOrderLabel" for="plate-param-peckOrder">
                  Peck Order
                </Label>
                <AvField id="plate-param-peckOrder" type="string" className="form-control" name="peckOrder" />
              </AvGroup>
              <AvGroup>
                <Label for="plate-param-queryPlate">Query Plate</Label>
                <AvInput id="plate-param-queryPlate" type="select" className="form-control" name="queryPlateId">
                  <option value="" key="0" />
                  {queryPlates
                    ? queryPlates.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="plate-param-cascader">Cascader</Label>
                <AvInput id="plate-param-cascader" type="select" className="form-control" name="cascaderId">
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
              <Button tag={Link} id="cancel-save" to="/plate-param" replace color="info">
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
  queryPlates: storeState.queryPlate.entities,
  cascaders: storeState.cascader.entities,
  plateParamEntity: storeState.plateParam.entity,
  loading: storeState.plateParam.loading,
  updating: storeState.plateParam.updating,
  updateSuccess: storeState.plateParam.updateSuccess,
});

const mapDispatchToProps = {
  getQueryPlates,
  getCascaders,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PlateParamUpdate);
