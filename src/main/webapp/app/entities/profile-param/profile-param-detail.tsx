import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './profile-param.reducer';
import { IProfileParam } from 'app/shared/model/profile-param.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProfileParamDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProfileParamDetail = (props: IProfileParamDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { profileParamEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          ProfileParam [<b>{profileParamEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="dataValue">Data Value</span>
          </dt>
          <dd>{profileParamEntity.dataValue}</dd>
          <dt>
            <span id="dataInt">Data Int</span>
          </dt>
          <dd>{profileParamEntity.dataInt}</dd>
          <dt>
            <span id="userEnteredCustomValue">User Entered Custom Value</span>
          </dt>
          <dd>{profileParamEntity.userEnteredCustomValue}</dd>
          <dt>Profile</dt>
          <dd>{profileParamEntity.profileName ? profileParamEntity.profileName : ''}</dd>
          <dt>Field</dt>
          <dd>{profileParamEntity.fieldFieldName ? profileParamEntity.fieldFieldName : ''}</dd>
          <dt>Cascader Param</dt>
          <dd>{profileParamEntity.cascaderParamParamTitle ? profileParamEntity.cascaderParamParamTitle : ''}</dd>
        </dl>
        <Button tag={Link} to="/profile-param" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/profile-param/${profileParamEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ profileParam }: IRootState) => ({
  profileParamEntity: profileParam.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProfileParamDetail);
