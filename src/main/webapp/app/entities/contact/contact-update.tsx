import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICascaderParam } from 'app/shared/model/cascader-param.model';
import { getEntities as getCascaderParams } from 'app/entities/cascader-param/cascader-param.reducer';
import { IProfile } from 'app/shared/model/profile.model';
import { getEntities as getProfiles } from 'app/entities/profile/profile.reducer';
import { getEntity, updateEntity, createEntity, reset } from './contact.reducer';
import { IContact } from 'app/shared/model/contact.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IContactUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ContactUpdate = (props: IContactUpdateProps) => {
  const [contactStatusId, setContactStatusId] = useState('0');
  const [senderId, setSenderId] = useState('0');
  const [receiverId, setReceiverId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { contactEntity, cascaderParams, profiles, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/contact');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCascaderParams();
    props.getProfiles();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.initiatedDate = convertDateTimeToServer(values.initiatedDate);
    values.updatedDate = convertDateTimeToServer(values.updatedDate);

    if (errors.length === 0) {
      const entity = {
        ...contactEntity,
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
          <h2 id="matriSearchApp.contact.home.createOrEditLabel">Create or edit a Contact</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : contactEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="contact-id">ID</Label>
                  <AvInput id="contact-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="initiatedDateLabel" for="contact-initiatedDate">
                  Initiated Date
                </Label>
                <AvInput
                  id="contact-initiatedDate"
                  type="datetime-local"
                  className="form-control"
                  name="initiatedDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.contactEntity.initiatedDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="updatedDateLabel" for="contact-updatedDate">
                  Updated Date
                </Label>
                <AvInput
                  id="contact-updatedDate"
                  type="datetime-local"
                  className="form-control"
                  name="updatedDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.contactEntity.updatedDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label for="contact-contactStatus">Contact Status</Label>
                <AvInput id="contact-contactStatus" type="select" className="form-control" name="contactStatusId">
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
                <Label for="contact-sender">Sender</Label>
                <AvInput id="contact-sender" type="select" className="form-control" name="senderId">
                  <option value="" key="0" />
                  {profiles
                    ? profiles.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="contact-receiver">Receiver</Label>
                <AvInput id="contact-receiver" type="select" className="form-control" name="receiverId">
                  <option value="" key="0" />
                  {profiles
                    ? profiles.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/contact" replace color="info">
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
  profiles: storeState.profile.entities,
  contactEntity: storeState.contact.entity,
  loading: storeState.contact.loading,
  updating: storeState.contact.updating,
  updateSuccess: storeState.contact.updateSuccess,
});

const mapDispatchToProps = {
  getCascaderParams,
  getProfiles,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ContactUpdate);
