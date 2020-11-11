import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './mala.reducer';
import { IMala } from 'app/shared/model/mala.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMalaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MalaUpdate = (props: IMalaUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { malaEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/mala');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...malaEntity,
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
          <h2 id="matriSearchApp.mala.home.createOrEditLabel">Create or edit a Mala</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : malaEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="mala-id">ID</Label>
                  <AvInput id="mala-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="malaNameLabel" for="mala-malaName">
                  Mala Name
                </Label>
                <AvField id="mala-malaName" type="text" name="malaName" />
              </AvGroup>
              <AvGroup check>
                <Label id="isEditableLabel">
                  <AvInput id="mala-isEditable" type="checkbox" className="form-check-input" name="isEditable" />
                  Is Editable
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="mala-description">
                  Description
                </Label>
                <AvField id="mala-description" type="text" name="description" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/mala" replace color="info">
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
  malaEntity: storeState.mala.entity,
  loading: storeState.mala.loading,
  updating: storeState.mala.updating,
  updateSuccess: storeState.mala.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MalaUpdate);
