import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IField } from 'app/shared/model/field.model';
import { getEntities as getFields } from 'app/entities/field/field.reducer';
import { getEntity, updateEntity, createEntity, reset } from './field-attribute.reducer';
import { IFieldAttribute } from 'app/shared/model/field-attribute.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFieldAttributeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FieldAttributeUpdate = (props: IFieldAttributeUpdateProps) => {
  const [fieldId, setFieldId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { fieldAttributeEntity, fields, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/field-attribute');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

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
        ...fieldAttributeEntity,
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
          <h2 id="matriSearchApp.fieldAttribute.home.createOrEditLabel">Create or edit a FieldAttribute</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : fieldAttributeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="field-attribute-id">ID</Label>
                  <AvInput id="field-attribute-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="attributeNameLabel" for="field-attribute-attributeName">
                  Attribute Name
                </Label>
                <AvField id="field-attribute-attributeName" type="text" name="attributeName" />
              </AvGroup>
              <AvGroup>
                <Label id="attributeValueLabel" for="field-attribute-attributeValue">
                  Attribute Value
                </Label>
                <AvField id="field-attribute-attributeValue" type="text" name="attributeValue" />
              </AvGroup>
              <AvGroup>
                <Label for="field-attribute-field">Field</Label>
                <AvInput id="field-attribute-field" type="select" className="form-control" name="fieldId">
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
              <Button tag={Link} id="cancel-save" to="/field-attribute" replace color="info">
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
  fields: storeState.field.entities,
  fieldAttributeEntity: storeState.fieldAttribute.entity,
  loading: storeState.fieldAttribute.loading,
  updating: storeState.fieldAttribute.updating,
  updateSuccess: storeState.fieldAttribute.updateSuccess,
});

const mapDispatchToProps = {
  getFields,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FieldAttributeUpdate);
