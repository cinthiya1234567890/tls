import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntity, updateEntity, createEntity, reset } from './profile.reducer';
import { IProfile } from 'app/shared/model/profile.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProfileUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProfileUpdate = (props: IProfileUpdateProps) => {
  const [userId, setUserId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { profileEntity, users, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/profile');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }

    props.getUsers();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.createdTime = convertDateTimeToServer(values.createdTime);
    values.loginTime = convertDateTimeToServer(values.loginTime);
    values.prevLoginTime = convertDateTimeToServer(values.prevLoginTime);

    if (errors.length === 0) {
      const entity = {
        ...profileEntity,
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
          <h2 id="matriSearchApp.profile.home.createOrEditLabel">Create or edit a Profile</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : profileEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="profile-id">ID</Label>
                  <AvInput id="profile-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="profile-name">
                  Name
                </Label>
                <AvField id="profile-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="dateOfBirthLabel" for="profile-dateOfBirth">
                  Date Of Birth
                </Label>
                <AvField id="profile-dateOfBirth" type="date" className="form-control" name="dateOfBirth" />
              </AvGroup>
              <AvGroup>
                <Label id="phoneLabel" for="profile-phone">
                  Phone
                </Label>
                <AvField id="profile-phone" type="text" name="phone" />
              </AvGroup>
              <AvGroup>
                <Label id="createdTimeLabel" for="profile-createdTime">
                  Created Time
                </Label>
                <AvInput
                  id="profile-createdTime"
                  type="datetime-local"
                  className="form-control"
                  name="createdTime"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.profileEntity.createdTime)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="loginTimeLabel" for="profile-loginTime">
                  Login Time
                </Label>
                <AvInput
                  id="profile-loginTime"
                  type="datetime-local"
                  className="form-control"
                  name="loginTime"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.profileEntity.loginTime)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="prevLoginTimeLabel" for="profile-prevLoginTime">
                  Prev Login Time
                </Label>
                <AvInput
                  id="profile-prevLoginTime"
                  type="datetime-local"
                  className="form-control"
                  name="prevLoginTime"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.profileEntity.prevLoginTime)}
                />
              </AvGroup>
              <AvGroup>
                <Label for="profile-user">User</Label>
                <AvInput id="profile-user" type="select" className="form-control" name="userId">
                  <option value="" key="0" />
                  {users
                    ? users.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.login}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/profile" replace color="info">
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
  users: storeState.userManagement.users,
  profileEntity: storeState.profile.entity,
  loading: storeState.profile.loading,
  updating: storeState.profile.updating,
  updateSuccess: storeState.profile.updateSuccess,
});

const mapDispatchToProps = {
  getUsers,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProfileUpdate);
