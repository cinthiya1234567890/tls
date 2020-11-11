import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './profile.reducer';
import { IProfile } from 'app/shared/model/profile.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProfileDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProfileDetail = (props: IProfileDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { profileEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Profile [<b>{profileEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{profileEntity.name}</dd>
          <dt>
            <span id="dateOfBirth">Date Of Birth</span>
          </dt>
          <dd>
            {profileEntity.dateOfBirth ? <TextFormat value={profileEntity.dateOfBirth} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="phone">Phone</span>
          </dt>
          <dd>{profileEntity.phone}</dd>
          <dt>
            <span id="createdTime">Created Time</span>
          </dt>
          <dd>
            {profileEntity.createdTime ? <TextFormat value={profileEntity.createdTime} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="loginTime">Login Time</span>
          </dt>
          <dd>{profileEntity.loginTime ? <TextFormat value={profileEntity.loginTime} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="prevLoginTime">Prev Login Time</span>
          </dt>
          <dd>
            {profileEntity.prevLoginTime ? <TextFormat value={profileEntity.prevLoginTime} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>User</dt>
          <dd>{profileEntity.userLogin ? profileEntity.userLogin : ''}</dd>
        </dl>
        <Button tag={Link} to="/profile" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/profile/${profileEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ profile }: IRootState) => ({
  profileEntity: profile.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProfileDetail);
