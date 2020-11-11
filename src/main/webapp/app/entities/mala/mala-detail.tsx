import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './mala.reducer';
import { IMala } from 'app/shared/model/mala.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMalaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MalaDetail = (props: IMalaDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { malaEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Mala [<b>{malaEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="malaName">Mala Name</span>
          </dt>
          <dd>{malaEntity.malaName}</dd>
          <dt>
            <span id="isEditable">Is Editable</span>
          </dt>
          <dd>{malaEntity.isEditable ? 'true' : 'false'}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{malaEntity.description}</dd>
        </dl>
        <Button tag={Link} to="/mala" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/mala/${malaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ mala }: IRootState) => ({
  malaEntity: mala.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MalaDetail);
